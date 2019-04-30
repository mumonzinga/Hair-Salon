import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;


public class App {

    public static void main(String[] args) {
        staticFileLocation("/public");
        String layout = "templates/layout.vtl";


        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        port(port);

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


        //retrieving stylists path
        get("/stylists", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("stylists", Stylist.all());
            model.put("template", "templates/stylists.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


        // retrieving new stylist form path
        get("/stylists/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/newStylistForm.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
        // posting a stylist
        post("/stylists", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            String gender = request.queryParams("gender");
            String contact = request.queryParams("contact");
            Stylist newStylist = new Stylist(name, gender, contact);
            newStylist.save();
            model.put("template", "templates/success.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


        //

        get("/stylists/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
            model.put("stylist", stylist);
            model.put("template", "templates/stylist.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        // creating new client in a stylist object
        get("stylists/:id/clients/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
            model.put("stylist", stylist);
            model.put("stylists", Stylist.all());
            model.put("template", "templates/newClientForm.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        //retrieving all clients
        get("/clients", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("clients", Client.all());
            model.put("template", "templates/clients.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        //retrieving a new client
        get("clients/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("stylists", Stylist.all());
            model.put("template", "templates/newClientForm.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
        // posting clients to clients.vtl
        post("/clients", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            String gender = request.queryParams("gender");
            String contact = request.queryParams("contact");
            int stylist_id = Integer.parseInt(request.queryParams("stylist_id"));
            Client newClient = new Client(name, gender, contact, stylist_id);
            newClient.save();

            model.put("template", "templates/success.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
        //retrieving  stylist form editor

        get("/stylists/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
            model.put("stylist", stylist);
            model.put("template", "templates/StylistFormEditor.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
        //updating the stylist object
        post("/stylists/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
            String name = request.queryParams("name");
            String gender = request.queryParams("gender");
            String contact = request.queryParams("contact");
            stylist.update(name, gender, contact);
            model.put("template", "templates/success.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        //deleting stylist object
        post("/stylists/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
            stylist.delete();
            response.redirect("/stylists");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
        //deleting client object
        post("/clients/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Client client = Client.find(Integer.parseInt(request.params(":id")));
            client.delete();
            response.redirect("/clients");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
        //updating clients objects
        post("/clients/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Client client = Client.find(Integer.parseInt(request.params(":id")));
            String name = request.queryParams("name");
            String gender = request.queryParams("gender");
            String contact = request.queryParams("contact");
            int stylist_id = Integer.parseInt(request.queryParams("stylist_id"));
            client.update(name, gender, contact, stylist_id);
            model.put("template", "templates/success.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
        //retrieving client edit form
        get("/clients/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Client client = Client.find(Integer.parseInt(request.params(":id")));
            model.put("client", client);
            model.put("stylists", Stylist.all());
            model.put("template", "templates/ClientFormEditor.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

    }

}
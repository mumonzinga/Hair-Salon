--
-- PostgreSQL database dump
--

-- Dumped from database version 10.7 (Ubuntu 10.7-0ubuntu0.18.04.1)
-- Dumped by pg_dump version 10.7 (Ubuntu 10.7-0ubuntu0.18.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: clients; Type: TABLE; Schema: public; Owner: ;
--

CREATE TABLE public.clients (
    id integer NOT NULL,
    name character varying,
    gender character varying,
    contact character varying,
    stylist_id integer
);


ALTER TABLE public.clients ;

--
-- Name: clients_id_seq; Type: SEQUENCE; Schema: public; Owner:
--

CREATE SEQUENCE public.clients_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.clients_id_seq

--
-- Name: clients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner:
--

ALTER SEQUENCE public.clients_id_seq OWNED BY public.clients.id;


--
-- Name: stylists; Type: TABLE; Schema: public; Owner: ;
--

CREATE TABLE public.stylists (
    id integer NOT NULL,
    name character varying,
    gender character varying,
    contact character varying
);


ALTER TABLE public.stylists

--
-- Name: stylists_id_seq; Type: SEQUENCE; Schema: public; Owner:
--

CREATE SEQUENCE public.stylists_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.stylists_id_seq

--
-- Name: stylists_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ;
--

ALTER SEQUENCE public.stylists_id_seq OWNED BY public.stylists.id;


--
-- Name: clients id; Type: DEFAULT; Schema: public; Owner:
--

ALTER TABLE ONLY public.clients ALTER COLUMN id SET DEFAULT nextval('public.clients_id_seq'::regclass);


--
-- Name: stylists id; Type: DEFAULT; Schema: public; Owner:
--

ALTER TABLE ONLY public.stylists ALTER COLUMN id SET DEFAULT nextval('public.stylists_id_seq'::regclass);


--
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner:
--

COPY public.clients (id, name, gender, contact, stylist_id) FROM stdin;
23	new client 	M	08262626	53
\.


--
-- Data for Name: stylists; Type: TABLE DATA; Schema: public; Owner:
--

COPY public.stylists (id, name, gender, contact) FROM stdin;
52	Yaskaa	F	0711937973
53	Cersei	F	0722935373
\.


--
-- Name: clients_id_seq; Type: SEQUENCE SET; Schema: public; Owner:
--

SELECT pg_catalog.setval('public.clients_id_seq', 23, true);


--
-- Name: stylists_id_seq; Type: SEQUENCE SET; Schema: public; Owner:
--

SELECT pg_catalog.setval('public.stylists_id_seq', 53, true);


--
-- Name: clients clients_pkey; Type: CONSTRAINT; Schema: public; Owner:
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- Name: stylists stylists_pkey; Type: CONSTRAINT; Schema: public; Owner:
--

ALTER TABLE ONLY public.stylists
    ADD CONSTRAINT stylists_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

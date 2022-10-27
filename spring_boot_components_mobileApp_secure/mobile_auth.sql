
--
-- TOC entry 197 (class 1259 OID 18460)
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    name character varying,
    id integer NOT NULL
);


ALTER TABLE public.role OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 18458)
-- Name: role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.role_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.role_id_seq OWNER TO postgres;

--
-- TOC entry 2838 (class 0 OID 0)
-- Dependencies: 196
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.role_id_seq OWNED BY public.role.id;


--
-- TOC entry 199 (class 1259 OID 18469)
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    id integer NOT NULL,
    username character varying,
    password character varying,
    algorithm character varying,
    business_user_id integer
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 18467)
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_id_seq OWNER TO postgres;

--
-- TOC entry 2839 (class 0 OID 0)
-- Dependencies: 198
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_id_seq OWNED BY public."user".id;


--
-- TOC entry 200 (class 1259 OID 18476)
-- Name: users_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users_roles (
    user_id integer NOT NULL,
    role_id integer NOT NULL
);


ALTER TABLE public.users_roles OWNER TO postgres;

--
-- TOC entry 2697 (class 2604 OID 18463)
-- Name: role id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role ALTER COLUMN id SET DEFAULT nextval('public.role_id_seq'::regclass);


--
-- TOC entry 2698 (class 2604 OID 18472)
-- Name: user id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user" ALTER COLUMN id SET DEFAULT nextval('public.user_id_seq'::regclass);


--
-- TOC entry 2829 (class 0 OID 18460)
-- Dependencies: 197
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.role (name, id) VALUES ('ROLE_OPERATOR', 1);
INSERT INTO public.role (name, id) VALUES ('ROLE_USER', 2);


--
-- TOC entry 2831 (class 0 OID 18469)
-- Dependencies: 199
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."user" (id, username, password, algorithm, business_user_id) VALUES (1, 'John', '{bcrypt}$2y$12$afiy2D.ALhPf0s3fW6BTJOvqrB7ZUfSYRamw8RDu6ol6zXsTLPRfy', 'BCRYPT', 1);
INSERT INTO public."user" (id, username, password, algorithm, business_user_id) VALUES (2, 'Paul', '{bcrypt}$2y$12$afiy2D.ALhPf0s3fW6BTJOvqrB7ZUfSYRamw8RDu6ol6zXsTLPRfy', 'BCRYPT', 2);
INSERT INTO public."user" (id, username, password, algorithm, business_user_id) VALUES (3, 'Mike', '{bcrypt}$2y$12$afiy2D.ALhPf0s3fW6BTJOvqrB7ZUfSYRamw8RDu6ol6zXsTLPRfy', 'BCRYPT', 3);


--
-- TOC entry 2832 (class 0 OID 18476)
-- Dependencies: 200
-- Data for Name: users_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO public.users_roles (user_id, role_id) VALUES (2, 2);


--
-- TOC entry 2840 (class 0 OID 0)
-- Dependencies: 196
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.role_id_seq', 3, true);


--
-- TOC entry 2841 (class 0 OID 0)
-- Dependencies: 198
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_id_seq', 4, true);


--
-- TOC entry 2700 (class 2606 OID 18480)
-- Name: role pk_role; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT pk_role PRIMARY KEY (id);


--
-- TOC entry 2702 (class 2606 OID 18482)
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- TOC entry 2704 (class 2606 OID 18484)
-- Name: users_roles users_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT users_roles_pkey PRIMARY KEY (user_id, role_id);


--
-- TOC entry 2705 (class 2606 OID 18485)
-- Name: users_roles role_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT role_fk FOREIGN KEY (role_id) REFERENCES public.role(id) NOT VALID;


--
-- TOC entry 2706 (class 2606 OID 18490)
-- Name: users_roles user_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES public."user"(id);


-- Completed on 2022-10-16 16:32:53

--
-- PostgreSQL database dump complete
--


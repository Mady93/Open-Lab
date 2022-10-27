--
-- TOC entry 197 (class 1259 OID 17924)
-- Name: business_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.business_user (
    id integer NOT NULL,
    firstname character varying NOT NULL,
    lastname character varying NOT NULL,
    fiscalcode character varying NOT NULL,
    version integer
);


ALTER TABLE public.business_user OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 17922)
-- Name: business_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.business_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.business_user_id_seq OWNER TO postgres;

--
-- TOC entry 2871 (class 0 OID 0)
-- Dependencies: 196
-- Name: business_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.business_user_id_seq OWNED BY public.business_user.id;


--
-- TOC entry 199 (class 1259 OID 17933)
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id integer NOT NULL,
    name character varying,
    version integer
);


ALTER TABLE public.product OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 17931)
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_id_seq OWNER TO postgres;

--
-- TOC entry 2872 (class 0 OID 0)
-- Dependencies: 198
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;


--
-- TOC entry 201 (class 1259 OID 17942)
-- Name: sim; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sim (
    msisdn character varying,
    imsi character varying,
    business_user_id integer,
    version integer,
    id integer NOT NULL
);


ALTER TABLE public.sim OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 17940)
-- Name: sim_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sim_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sim_id_seq OWNER TO postgres;

--
-- TOC entry 2873 (class 0 OID 0)
-- Dependencies: 200
-- Name: sim_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.sim_id_seq OWNED BY public.sim.id;


--
-- TOC entry 202 (class 1259 OID 17949)
-- Name: sim_product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sim_product (
    sim_id integer NOT NULL,
    product_id integer NOT NULL
);


ALTER TABLE public.sim_product OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 17954)
-- Name: validation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.validation (
    id integer NOT NULL,
    date timestamp with time zone,
    business_user_id integer,
    version integer
);


ALTER TABLE public.validation OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 17952)
-- Name: validation_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.validation_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.validation_id_seq OWNER TO postgres;

--
-- TOC entry 2874 (class 0 OID 0)
-- Dependencies: 203
-- Name: validation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.validation_id_seq OWNED BY public.validation.id;


--
-- TOC entry 2710 (class 2604 OID 17927)
-- Name: business_user id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.business_user ALTER COLUMN id SET DEFAULT nextval('public.business_user_id_seq'::regclass);


--
-- TOC entry 2711 (class 2604 OID 17936)
-- Name: product id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);


--
-- TOC entry 2712 (class 2604 OID 17945)
-- Name: sim id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sim ALTER COLUMN id SET DEFAULT nextval('public.sim_id_seq'::regclass);


--
-- TOC entry 2713 (class 2604 OID 17957)
-- Name: validation id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.validation ALTER COLUMN id SET DEFAULT nextval('public.validation_id_seq'::regclass);


--
-- TOC entry 2858 (class 0 OID 17924)
-- Dependencies: 197
-- Data for Name: business_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.business_user (id, firstname, lastname, fiscalcode, version) VALUES (394, 'Mario', 'Rossi', 'ASDFGHJ', 0);
INSERT INTO public.business_user (id, firstname, lastname, fiscalcode, version) VALUES (395, 'Luigi', 'Rossi', 'VBDGSH', 0);
INSERT INTO public.business_user (id, firstname, lastname, fiscalcode, version) VALUES (397, 'Mario', 'Rossi', 'QWEERTY', 0);


--
-- TOC entry 2860 (class 0 OID 17933)
-- Dependencies: 199
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.product (id, name, version) VALUES (38, 'GIGA 1000', 0);
INSERT INTO public.product (id, name, version) VALUES (39, 'GIGA EXTRA', 0);


--
-- TOC entry 2862 (class 0 OID 17942)
-- Dependencies: 201
-- Data for Name: sim; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.sim (msisdn, imsi, business_user_id, version, id) VALUES ('3389263829', '1200000012131', NULL, 0, 3);
INSERT INTO public.sim (msisdn, imsi, business_user_id, version, id) VALUES ('3390192649', '8000000012120', NULL, 0, 4);
INSERT INTO public.sim (msisdn, imsi, business_user_id, version, id) VALUES ('3321254821', '7282931012939', NULL, 0, 5);
INSERT INTO public.sim (msisdn, imsi, business_user_id, version, id) VALUES ('3388881888', '2810291021220', NULL, 0, 6);
INSERT INTO public.sim (msisdn, imsi, business_user_id, version, id) VALUES ('3394442718', '2823102822320', NULL, 0, 7);
INSERT INTO public.sim (msisdn, imsi, business_user_id, version, id) VALUES ('3322288271', '7281039202001', NULL, 0, 8);
INSERT INTO public.sim (msisdn, imsi, business_user_id, version, id) VALUES ('3387272849', '8202983920120', NULL, 0, 9);
INSERT INTO public.sim (msisdn, imsi, business_user_id, version, id) VALUES ('3395432067', '2833829172202', NULL, 0, 10);
INSERT INTO public.sim (msisdn, imsi, business_user_id, version, id) VALUES ('3329826282', '8687968594849', NULL, 16, 2);
INSERT INTO public.sim (msisdn, imsi, business_user_id, version, id) VALUES ('3392821931', '1010123102120', 394, 89, 1);
INSERT INTO public.sim (msisdn, imsi, business_user_id, version, id) VALUES ('1234', '1234', NULL, 0, 12);
INSERT INTO public.sim (msisdn, imsi, business_user_id, version, id) VALUES ('123', '123', 395, 5, 11);


--
-- TOC entry 2863 (class 0 OID 17949)
-- Dependencies: 202
-- Data for Name: sim_product; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.sim_product (sim_id, product_id) VALUES (11, 38);


--
-- TOC entry 2865 (class 0 OID 17954)
-- Dependencies: 204
-- Data for Name: validation; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.validation (id, date, business_user_id, version) VALUES (4, '2022-10-25 20:25:43.511+02', 395, 0);


--
-- TOC entry 2875 (class 0 OID 0)
-- Dependencies: 196
-- Name: business_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.business_user_id_seq', 397, true);


--
-- TOC entry 2876 (class 0 OID 0)
-- Dependencies: 198
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_id_seq', 39, true);


--
-- TOC entry 2877 (class 0 OID 0)
-- Dependencies: 200
-- Name: sim_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sim_id_seq', 12, true);


--
-- TOC entry 2878 (class 0 OID 0)
-- Dependencies: 203
-- Name: validation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.validation_id_seq', 4, true);


--
-- TOC entry 2729 (class 2606 OID 17961)
-- Name: validation business_user_id_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.validation
    ADD CONSTRAINT business_user_id_unique UNIQUE (business_user_id);


--
-- TOC entry 2715 (class 2606 OID 17963)
-- Name: business_user business_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.business_user
    ADD CONSTRAINT business_user_pkey PRIMARY KEY (id);


--
-- TOC entry 2717 (class 2606 OID 17959)
-- Name: business_user fiscalcode_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.business_user
    ADD CONSTRAINT fiscalcode_unique UNIQUE (fiscalcode);


--
-- TOC entry 2721 (class 2606 OID 17965)
-- Name: sim id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sim
    ADD CONSTRAINT id_pk PRIMARY KEY (id);


--
-- TOC entry 2723 (class 2606 OID 17967)
-- Name: sim imsi_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sim
    ADD CONSTRAINT imsi_unique UNIQUE (imsi) INCLUDE (imsi);


--
-- TOC entry 2725 (class 2606 OID 17969)
-- Name: sim msisdn_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sim
    ADD CONSTRAINT msisdn_unique UNIQUE (msisdn) INCLUDE (msisdn);


--
-- TOC entry 2719 (class 2606 OID 17971)
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- TOC entry 2727 (class 2606 OID 17973)
-- Name: sim_product sim_product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sim_product
    ADD CONSTRAINT sim_product_pkey PRIMARY KEY (sim_id, product_id);


--
-- TOC entry 2731 (class 2606 OID 17975)
-- Name: validation validation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.validation
    ADD CONSTRAINT validation_pkey PRIMARY KEY (id);


--
-- TOC entry 2735 (class 2606 OID 17976)
-- Name: validation business_user_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.validation
    ADD CONSTRAINT business_user_fk FOREIGN KEY (business_user_id) REFERENCES public.business_user(id) NOT VALID;


--
-- TOC entry 2732 (class 2606 OID 17981)
-- Name: sim business_user_foreign_key; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sim
    ADD CONSTRAINT business_user_foreign_key FOREIGN KEY (business_user_id) REFERENCES public.business_user(id);


--
-- TOC entry 2733 (class 2606 OID 17986)
-- Name: sim_product product_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sim_product
    ADD CONSTRAINT product_fk FOREIGN KEY (product_id) REFERENCES public.product(id) NOT VALID;


--
-- TOC entry 2734 (class 2606 OID 17991)
-- Name: sim_product sim_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sim_product
    ADD CONSTRAINT sim_fk FOREIGN KEY (sim_id) REFERENCES public.sim(id) NOT VALID;


-- Completed on 2022-10-16 16:35:15

--
-- PostgreSQL database dump complete
--


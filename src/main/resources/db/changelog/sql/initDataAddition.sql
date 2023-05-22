INSERT INTO public.materials (material_id, material) VALUES (1, 'Пластик');
INSERT INTO public.materials (material_id, material) VALUES (2, 'Аллой');
INSERT INTO public.materials (material_id, material) VALUES (3, 'Золото');
INSERT INTO public.materials (material_id, material) VALUES (4, 'Латунь');
INSERT INTO public.materials (material_id, material) VALUES (5, 'Сталь');
INSERT INTO public.materials (material_id, material) VALUES (6, 'Титан');

INSERT INTO public.mech_types (mech_id, type) VALUES (1, 'Электронные');
INSERT INTO public.mech_types (mech_id, type) VALUES (2, 'Кварцевые');
INSERT INTO public.mech_types (mech_id, type) VALUES (3, 'Механические');
INSERT INTO public.mech_types (mech_id, type) VALUES (4, 'Гибридные');

INSERT INTO public.stamp (stamp_id, stamp) VALUES (1, 'Seiko');
INSERT INTO public.stamp (stamp_id, stamp) VALUES (2, 'Titan');
INSERT INTO public.stamp (stamp_id, stamp) VALUES (3, 'Omega');
INSERT INTO public.stamp (stamp_id, stamp) VALUES (4, 'Timex');
INSERT INTO public.stamp (stamp_id, stamp) VALUES (5, 'Citizen');
INSERT INTO public.stamp (stamp_id, stamp) VALUES (6, 'Tag Heuer');
INSERT INTO public.stamp (stamp_id, stamp) VALUES (7, 'Patek Phillipe');
INSERT INTO public.stamp (stamp_id, stamp) VALUES (8, 'Oakley');
INSERT INTO public.stamp (stamp_id, stamp) VALUES (9, 'Rolex');
INSERT INTO public.stamp (stamp_id, stamp) VALUES (10, 'Casio');

INSERT INTO public.products (product_id, name, description, release_date, image_url, material_id, mech_id, price, stamp_id) VALUES (1, 'Часы CASIO EF-125D-2A', null, '2020-11-27', 'https://disk.yandex.ru/i/BXC1g4Ihpjb-8Q', 5, 1, 5390, 10);
INSERT INTO public.products (product_id, name, description, release_date, image_url, material_id, mech_id, price, stamp_id) VALUES (2, 'Seiko 5 Sports', null, '2021-11-28', 'https://disk.yandex.ru/i/02fULOmh1aJcsw', 5, 3, 27500, 1);
INSERT INTO public.products (product_id, name, description, release_date, image_url, material_id, mech_id, price, stamp_id) VALUES (3, 'Смарт-часы Geozon Titan Black/Black (G-SM06BLK)', null, '2022-02-04', 'https://disk.yandex.ru/i/a11LWni3JWNPLA', 1, 1, 1790, 2);
INSERT INTO public.products (product_id, name, description, release_date, image_url, material_id, mech_id, price, stamp_id) VALUES (4, 'OMEGA 13110286005001', null, '2021-09-12', 'https://disk.yandex.ru/i/1aUs38zaKa70yg', 5, 2, 285000, 3);
INSERT INTO public.products (product_id, name, description, release_date, image_url, material_id, mech_id, price, stamp_id) VALUES (5, 'Timex TW2R96400VN', null, '2021-12-16', 'https://disk.yandex.ru/i/MqIe_g9E6N_8mw', 5, 2, 9450, 4);
INSERT INTO public.products (product_id, name, description, release_date, image_url, material_id, mech_id, price, stamp_id) VALUES (6, 'Citizen EM0643-84X', null, '2021-06-18', 'https://disk.yandex.ru/i/M3kJ2OwfKigQCA', 3, 2, 40350, 5);
INSERT INTO public.products (product_id, name, description, release_date, image_url, material_id, mech_id, price, stamp_id) VALUES (7, 'TAG Heuer CBL2111.BA0644', null, '2020-12-12', 'https://disk.yandex.ru/i/_9pJqb3J8RnQqg', 5, 3, 495000, 6);
INSERT INTO public.products (product_id, name, description, release_date, image_url, material_id, mech_id, price, stamp_id) VALUES (8, 'Patek Philippe Grand Complications 5372P-010', null, '2021-08-19', 'https://disk.yandex.ru/i/em1SWz1rEZGLMA', 2, 3, 325000, 7);
INSERT INTO public.products (product_id, name, description, release_date, image_url, material_id, mech_id, price, stamp_id) VALUES (9, 'Oakley 10-071 12 Gauge Titanium', null, '2020-07-23', 'https://disk.yandex.ru/i/Q84VmSc5K6kDDw', 6, 2, 23400, 8);
INSERT INTO public.products (product_id, name, description, release_date, image_url, material_id, mech_id, price, stamp_id) VALUES (10, 'Oyster Perpetual Cosmograph Daytona', null, '2021-08-28', 'https://disk.yandex.ru/i/hy6Qg6AHlMn49g', 3, 3, 2340000, 9);


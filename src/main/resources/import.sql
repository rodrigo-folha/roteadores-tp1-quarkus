-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;


insert into estado (nome, sigla) values('Tocantins', 'TO');
insert into estado (nome, sigla) values('Goiás', 'GO');
insert into estado (nome, sigla) values('São Paulo', 'SP');
insert into estado (nome, sigla) values('Rio de Janeiro', 'RJ');

insert into cidade (nome, id_estado) values('Palmas', 1);
insert into cidade (nome, id_estado) values('Paraiso', 1);
insert into cidade (nome, id_estado) values('Porto Nacional', 1);
insert into cidade (nome, id_estado) values('Campinas', 3);

insert into bandafrequencia (nome) values('Single-Band');
insert into bandafrequencia (nome) values('Dual-Band');
insert into bandafrequencia (nome) values('Tri-Band');
insert into bandafrequencia (nome) values('Quad-Band');

insert into protocoloseguranca (nome) values('WPA2-PSK');
insert into protocoloseguranca (nome) values('WPA3');
insert into protocoloseguranca (nome) values('WEP');
insert into protocoloseguranca (nome) values('WPA-ENTERPRISE');
insert into protocoloseguranca (nome) values('WPA-PSK');
insert into protocoloseguranca (nome) values('WPA2-ENTERPRISE');
insert into protocoloseguranca (nome) values('WPS');

insert into quantidadeantena (quantidade) values(1);
insert into quantidadeantena (quantidade) values(2);
insert into quantidadeantena (quantidade) values(3);
insert into quantidadeantena (quantidade) values(4);
insert into quantidadeantena (quantidade) values(5);
insert into quantidadeantena (quantidade) values(6);

insert into sinalwireless (nome) values('Wi-Fi 5');
insert into sinalwireless (nome) values('Wi-Fi 6');

insert into sistemaoperacional (nome) values('Cisco IOS');
insert into sistemaoperacional (nome) values('Juno OS');
insert into sistemaoperacional (nome) values('RouterOS');
insert into sistemaoperacional (nome) values('ZyNOS');

insert into roteador (nome, descricao, preco, id_sinalWireless, id_sistemaOperacional, id_bandaFrequencia, id_protocoloSeguranca, id_quantidadeAntena) values ('ROT WIFI GIGA AC1200MBPS 4ANT Tenda AC8', 'O AC8 é um roteador sem fio de banda dupla de Gigabit projetado especialmente para famílias com acesso por fibra. Equipado com portas de Gigabit, suporta acesso de largura de banda de até 1000 Mbps. A banda dupla fornece freqüência de 1167Mbps, utiliza totalmente a fibra de Gigabit.', 394.87, 1, 1, 2, 7, 4);
insert into roteador (nome, descricao, preco, id_sinalWireless, id_sistemaOperacional, id_bandaFrequencia, id_protocoloSeguranca, id_quantidadeAntena) values ('Roteador de Firewall Cisco RV110W-A-NA-K9 Small Business RV110W Wireless N VPN', 'Firewall Cisco rv110w wireless n vpn', 899.90, 1, 1, 1, 7, 2);
insert into roteador (nome, descricao, preco, id_sinalWireless, id_sistemaOperacional, id_bandaFrequencia, id_protocoloSeguranca, id_quantidadeAntena) values ('Roteador TP Link Mesh Gigabit Wi-Fi 5GHz Archer C6', 'Roteador TP-LINK Wireless Gigabit AC1200 Archer C6', 229.90, 1, 3, 2, 7, 4);
insert into roteador (nome, descricao, preco, id_sinalWireless, id_sistemaOperacional, id_bandaFrequencia, id_protocoloSeguranca, id_quantidadeAntena) values ('SonicWall NSA 4700', 'Derrotar ameaças avançadas requer uma solução avançada de firewall criada para as necessidades da sua empresa. O firewall de médio alcance do dispositivo de segurança de rede Sonicwall (NSa) é de segurança de última geração projetado especificamente para empresas de 250 usuários ou mais. Trabalhe com a confiança de saber que você está protegido contra as incursões do dia a dia, bem como contra ameaças avançadas como ransomware, ataques contra portas não padrão e violações de firewalls, tudo na velocidade dos negócios.', 54763.35, 1, 4, 2, 7, 1);
insert into roteador (nome, descricao, preco, id_sinalWireless, id_sistemaOperacional, id_bandaFrequencia, id_protocoloSeguranca, id_quantidadeAntena) values ('TP-Link Sistema Wi-Fi de malha residencial Deco X60 WiFi 6 AX3000', 'Sistema Wi-Fi de malha residencial TP-Link Deco X60 WiFi 6 AX3000, pacote com 3', 2820.00, 1, 2, 2, 1, 1);
insert into roteador (nome, descricao, preco, id_sinalWireless, id_sistemaOperacional, id_bandaFrequencia, id_protocoloSeguranca, id_quantidadeAntena) values ('Roteador AX5400 Wi-Fi 6 TP-Link Archer AX72, Dual Band 2.4/5 GHz, Conecta até 100+ Dispositivos, Até 270M² de Cobertura, Portas Full Gigabit, MU-MIMO, Beamforming', 'Roteador Wi-Fi Gigabit TP-Link ARCHER AX72. O Archer AX72 cria uma rede confiável e extremamente rápida, com tecnologia Wi-Fi 802.11ax. A banda de 2,4 GHz oferece velocidades até 574Mbps, pronto para tarefas diárias como e-mails e navegação na web, enquanto a banda de 5GHz oferece velocidades de até 4804Mbps, ideal para streaming de vídeo HD e jogos on-line lag. Com uma porta Gigabit WAN e quatro portas Gigabit LAN, as velocidades podem ser até 10 × mais rápidas do que as conexões Ethernet padrão. 6 antenas externas enviam sinais de Wi-Fi para todos os cantos da sua casa. O mais recente protocolo de segurança Wi-Fi, WPA3, traz novos recursos para melhorar a segurança cibernética em redes pessoais.', 629.90, 2, 3, 2, 2, 6);
insert into roteador (nome, descricao, preco, id_sinalWireless, id_sistemaOperacional, id_bandaFrequencia, id_protocoloSeguranca, id_quantidadeAntena) values ('TP-Link AC 1350 Archer C60 Roteador Wireless Dual Band, Branco', 'Roteador Tplink Archer C60 D.Band AC1350 4P Lan 1WAN 5Antena - PN # Archer C60.Um produto de verdadeiro desempenho. Fácilidade no manuseio e conforto ao uso.Fabricante líder de produtos e acessórios de alta qualidade.', 467.58, 1, 3, 3, 7, 5);
insert into roteador (nome, descricao, preco, id_sinalWireless, id_sistemaOperacional, id_bandaFrequencia, id_protocoloSeguranca, id_quantidadeAntena) values ('Roteador Wireless N TL-WR949N', 'O Roteador Wireless N TL-WR949N oferece velocidade de até 450Mbps, ideal para streaming, jogos online e conexões simultâneas. Possui 3 antenas de alto ganho, proporcionando cobertura Wi-Fi estável.M&E', 289.98, 1, 3, 4, 7, 3);

insert into fornecedor (nome, cnpj, email) values ('Prefeitura de Palmas', '24851511000185', 'palmas@to.gov.br');
insert into fornecedor (nome, cnpj, email) values ('Estado do Tocantins', '04592992000144', 'estado@to.gov.br');

insert into endereco(logradouro, bairro, numero, cep, complemento, id_cidade, id_fornecedor) values ('Av. Teotônio Segurado', 'Plano Diretor Norte', '01', '77006470', 'Edificio Nobre Empresarial', 1, 1);
insert into endereco(logradouro, bairro, numero, cep, complemento, id_cidade, id_fornecedor) values ('Esplanada Praça dos Girassóis', 'Plano Diretor Norte', '01', '77001902', 'Esplanada das Secretarias', 1, 2);

insert into telefone(codigoarea, numero, id_fornecedor) values ('63', '32127144', 1);
insert into telefone(codigoarea, numero, id_fornecedor) values ('63', '32127145', 1);
insert into telefone(codigoarea, numero, id_fornecedor) values ('63', '32127146', 1);
insert into telefone(codigoarea, numero, id_fornecedor) values ('63', '32181111', 2);
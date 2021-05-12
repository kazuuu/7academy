# 7comm-nerDevs
RESTful Web Service with Spring Boot and Clean Architecture

**OBS: Este é um projeto é didatico realizado pela 7Academy - centro de treinamentos da 7COMm.**

## O seu objetivo ##
O nerDevs uma aplicação onde usuários que pretendem ser estagiários podem se cadastrar, e as empresas que estão a procura de estagiários podem postar vagas de emprego.

### Projeto realizado para o graduacao de Java da 7Academy ###
Neste projeto, estamos utilizando o framework Spring Boot Web para desenvolvermos uma Rest API.

As classes e códigos estão organizados com base na arquitetura limpa (Clean Archtecture)

## TO DO ##

### Será necessário fazer um CRUD básico para ###
 -empresas;
 -vagas - não necessariamente apenas uma - delimitado para empresas;
 -reunião - delimitada a empresas;
 -usuarios;

### Suas regras de negócio serão ###

-O usuário deve procurar empresas que tem interesse;
-As vagas publicadas requerem informações básicas (NotNull);
-A empresa terá acesso aos usuarios que demonstrarm interesse na vaga - e selecionará alguns para passar pelos processos(interno a empresa);
-a responsabilidade de acompanhar os status é do usuário
- caso o usuário seja aprovado no processo ele receberá uma notificação da empresa com os documentos
 necessários e o meio deve entrar em contato
-os usuário terão acesso ao status em relação a vaga (recusado/pendente para participar processo, participando/aprovado no processo)
-um calendário com horário de reuniões e a plataforma que será feita;
-usuários selecionados devem confirmar ou recusar presença;
-você apenas pode ver vagas que você mesmo criou ( empresa );




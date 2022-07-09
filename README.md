# Gesto de RH (JavaFX-JDBC)

O Gestor de RH é uma aplicação para desktop desenvolvia na linguagem de programação Java que possibilita gerenciar duas áreas principais de uma empresa, ou seja, departamentos e
funcionários. O administrador poderá cadastrar, editar ou excluir qualquer departamento ou funcionário do sistema. Será possível vincular departamentos
aos funcionários previamente cadastrados para um melhor gerenciamento.

### 📋 Pré-requisitos

* OpenJDK 17 ou superior.
* MySQL Workbench 8.0 CE.
* Script do Banco de Dados (MySQL).
* JavaFX SDK 17 ou superior.
* Arquivo "gestor-rh.jar".
* Arquivo "db.properties".
* Arquivo "mysql-connector-java-8.0.28".

(Obs: Todos os arquivos menos o MySQL Workbench 8.0 CE está disponível para download através desse link: https://drive.google.com/file/d/1t05MlrvPis3viw3OsZnKXANhVDxBlsPc/view?usp=sharing)

### 🔧 Instalação

* 1º Passo: Instalar o OpenJDK 17 ou JavaJDK 17.
* 2º Passo: Instalar o MySQL Workbench 8.0 CE.
* 3º Passo: Adicionar o Script do Banco de Dados no MySQL Workbench 8.0 CE.
* 4º Passo: Criar uma variável de ambiente "JAVA_HOME" (Ex: C:\Program Files\Java\jdk-17.0.2).
* 5º Passo: Copiar os arquivos do JavaFX SDK para uma pasta de sua preferência.
* 6º Passo: Criar uma variável de ambiente "PATH_TO_FX" (Ex: C:\java-libs\javafx-sdk\lib).
* 7º Passo: Adicionar o "mysql-connector-java-8.0.28" dentro do diretório do Java FX (\lib).
* 8º Passo: Copiar o arquivo "gestor-rh.jar" e o "db.properties" para uma pasta de sua preferência (Ex: C:\Users\User\Desktop\Gestor-RH)
* 9º Passo: Abrir o CMD (Prompt de Comando) do Windows, após isso navegue até o diretório (comando cd\) onde estão os arquivos "gestor-rh.jar" e o "db.properties" (Ex: C:\Users\User\Desktop\Gestor-RH).
* 10º Passo: Após abrir o diretório pelo CMD execute o seguinte comando: java --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml -cp gestor-rh.jar
application.Main
* 11º Passo: Divirta-se!

## 🛠️ Construído com

* Eclipse IDE
* MySQL Workbench 8.0 CE

## ✒️ Autores

* Romualdo Júnior Freitas Leite
* Creiverson Vieira de Magalhães
* Elanne Maria Rocha dos Santos
* Sarah de Araújo Quintino Furtado

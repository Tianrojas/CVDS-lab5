# LABORATORIO 5 - MVC PRIMEFACES INTRODUCTION
**Nombre:** Sebastian Rojas Bueno

## PARTE I. - JUGANDO A SER UN CLIENTE HTTP

1. **Abra una terminal Linux o consola de comandos Windows.**

2. **Realice una conexión síncrona TCP/IP a través de Telnet al siguiente servidor:**
   * **Host: `www.escuelaing.edu.co`**
   * **Puerto: 80** 
   
   **Teniendo en cuenta los parámetros del comando telnet:**
 `telnet HOST PORT`
   
   Se hizo el procedimiento desde una maquina virtual linux ingresando el siguiente comando
   ```
   telnet www.escuelaing.edu.co 80
   ```

3. **Antes de que el servidor cierre la conexión por falta de comunicación:**
   * **Revise la página 36 del [RFC del protocolo HTTP](https://www.rfc-editor.org/rfc/rfc2616), sobre cómo realizar una petición GET. Con esto, solicite al servidor el recurso `sssss/abc.html`,usando la versión 1.0 de HTTP.**
   
      No obstante se ingreso el siguiente comando para realizar la petición GET al servidor
      ```
      GET /sssss/abc.html HTTP/1.0
      ```
     
   * **Asegúrese de presionar ENTER dos veces después de ingresar el comando.**
   * **Revise el resultado obtenido. ¿Qué codigo de error sale?, revise el significado del mismo en la [lista de códigos de estado HTTP](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes).**
     
     Se obtuvo el siguiente resultado: \
     ![image](https://user-images.githubusercontent.com/62759668/196054769-9565806b-bd88-4050-a223-eb383fe99d05.png) \
     El codigo del error retornante es el **Error 400**: Bad request, Indica que el sitio web o Host, no reconoce el comando o la solicitud del cliente porque esta no respeta el protocolo HTTP, por ende no la puede procesar.
     
   * **¿Qué otros códigos de error existen?, ¿En qué caso se manejarán?**
     
     Existen una gama de errores los cuales pueden verse desde una perspectiva mas amplia con la siguiente imagen:
     ![image](https://user-images.githubusercontent.com/62759668/196054928-5ec8235d-c4e7-4789-98af-616f64cdb216.png)


4. **Realice una nueva conexión con telnet, esta vez a:**
   * **Host: `www.httpbin.org`**
   * **Puerto: 80**
   * **Versión HTTP: 1.1** 
   
   **Ahora, solicite (GET) el recurso `/html`. ¿Qué se obtiene como resultado?**
   
   Se procede a correr la siguiente instruccion
   ```
   telnet www.httpbin.org 80
   ```
   junto el GET descrito por 
   ```
   GET /html HTTP/1.0
   ```
   Obteniendo el siguiente resultado
   ![image](https://user-images.githubusercontent.com/62759668/196055266-ea23928c-0ecc-42a8-9f58-46c4b10daf45.png)
   
   
**¡Muy bien!, ¡Acaba de usar del protocolo HTTP sin un navegador Web!. Cada vez que se usa un navegador, éste se conecta a un servidor HTTP, envía peticiones
(del protocolo HTTP), espera el resultado de las mismas, y -si se trata de contenido HTML- lo interpreta y dibuja.**

5. **Seleccione el contenido HTML de la respuesta y copielo al cortapapeles CTRL-SHIFT-C. Ejecute el comando wc (word count) para contar palabras con la
opción -c para contar el número de caracteres:**
   ```
   wc -c 
   ```
   **Pegue el contenido del portapapeles con CTRL-SHIFT-V y presione CTRL-D (fin de archivo de Linux). Si no termina el comando wc presione CTRL-D de
nuevo. No presione mas de dos veces CTRL-D indica que se termino la entrada y puede cerrarle la terminal. Debe salir el resultado de la cantidad de
caracteres que tiene el contenido HTML que respondió el servidor.** \
   ![image](https://user-images.githubusercontent.com/62759668/196056277-1ea4840e-617a-4b20-bad2-b4b5b7d074fc.png)
   **Claro está, las peticiones GET son insuficientes en muchos casos. Investigue: ¿Cuál es la diferencia entre los verbos GET y POST?** \
   
   * **GET:** El método GET lleva una representación de un recurso específico que se encuentran almacenados en un servidor al usuario. Las peticiones que usan el método GET sólo deben recuperar dato, permite obtener información del servidor. Es decir, traer datos que están almacenadas en el servidor, ya sea una base de datos o archivo al cliente. 
   * **POST:** El método POST se utiliza para enviar una entidad a un recurso en específico, causando a menudo un cambio en el estado o efectos secundarios en el servidor. Envia información desde el cliente para que sea procesada y actualice o agregue información en el servidor, como sería la carga o actualización.
   * **Diferencias:** El método GET lleva los datos usando la URL de forma visible, el método POST los envía de forma que no podemos verlos (en un segundo plano u "ocultos" al usuario)
   
   **¿Qué otros tipos de peticiones existen?** \
   ![image](https://user-images.githubusercontent.com/62759668/196057229-9c892020-0227-4a2c-bf99-609d7e4355e8.png)
   
6. **En la practica no se utiliza telnet para hacer peticiones a sitios web sino el comando curl con ayuda de la linea de comandos:**
   ```
   curl www.httpbin.org
   ```
   ![image](https://user-images.githubusercontent.com/62759668/196057506-f54385e7-16c5-470f-9735-9f4ef4911b43.png)

   **Utilice ahora el parámetro -v y con el parámetro -i:**
   ```
   curl -v www.httpbin.org
   ```
   ![image](https://user-images.githubusercontent.com/62759668/196057560-be5e4022-25f2-4638-ba87-4c192e7179c1.png)

   ```
   curl -i www.httpbin.org
   ```
   ![image](https://user-images.githubusercontent.com/62759668/196057602-a594db05-cb3b-4223-b131-fa35c43ffc82.png)

   **¿Cuáles son las diferencias con los diferentes parámetros?**
   
   * **curl:** El comando curl retorna únicamente el contenido del recurso solicitado. Su objetivo es transferir datos, sin interacción del usuario, hacia o desde un servidor.
   * **curl -v:** Nos permite obtener el encabezado de la solicitud y el número de la respuesta obtenida.El modo detallado -v también hará que curl muestre todos los encabezados que envía y recibe. Permite visualizar la petición GET y el Host realizado para obtener el contenido.
   * **curl -i:** Incluye el encabezado HTTP en la salida. El encabezado HTTP incluye cosas como nombre del servidor, fecha del documento, versión HTTP etc.
   
## PARTE II. - HACIENDO UNA APLICACIÓN WEB DINÁMICA A BAJO NIVEL.

**En este ejercicio, va a implementar una aplicación Web muy básica, haciendo uso de los elementos de más bajo nivel de Java-EE (Enterprise Edition), con el fin
de revisar los conceptos del protocolo HTTP. En este caso, se trata de un módulo de consulta de clientes Web que hace uso de una librería de acceso a datos
disponible en un repositorio Maven local.**

**Para esto, cree un proyecto maven nuevo usando el arquetipo de aplicación Web estándar** `maven-archetype-webapp` **y realice lo siguiente:**

Se utiliza el siguiente comando
```
mvn archetype:generate -Dfilter=maven-archetype-webapp
```
Con los parametros:

* Grupo: edu.eci.cvds
* Id del Artefacto: Servlet
* Paquete: edu.eci.cvds.servlet
* archetypeArtifactId: maven-archetype-webapp

De la siguiente manera:

![image](https://user-images.githubusercontent.com/62759668/196061854-5fe79950-0da3-4d1d-a32a-18c334cda908.png)
![image](https://user-images.githubusercontent.com/62759668/196061875-24736f9b-7e3a-49a3-a937-4047aa1c41fb.png)


1. **Revise la clase SampleServlet incluida a continuacion, e identifique qué hace:**
   ```
   package edu.eci.cvds.servlet;
   import java.io.IOException;
   import java.io.Writer;
   import java.util.Optional;
   import javax.servlet.ServletException;
   import javax.servlet.annotation.WebServlet;
   import javax.servlet.http.HttpServlet;
   import javax.servlet.http.HttpServletRequest;
   import javax.servlet.http.HttpServletResponse;
   @WebServlet(
      urlPatterns = "/helloServlet"
   )
   public class SampleServlet extends HttpServlet{
        static final long serialVersionUID = 35L;
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Writer responseWriter = resp.getWriter();
        Optional<String> optName = Optional.ofNullable(req.getParameter("name"));
        String name = optName.isPresent() && !optName.get().isEmpty() ? optName.get() : "";
        resp.setStatus(HttpServletResponse.SC_OK);
        responseWriter.write("Hello" + name + "!");
        responseWriter.flush();
        }
   }
   ```
   **Revise qué valor tiene el parámetro ‘urlPatterns’ de la anotación [@WebServlet](https://docs.oracle.com/javaee/6/tutorial/doc/bnafu.html), pues este indica qué URLs atiende las peticiones el servlet**


## Bibliografia
* [Métodos GET VS POST](https://es.stackoverflow.com/questions/34904/cuando-debo-usar-los-m%C3%A9todos-post-y-get)

   

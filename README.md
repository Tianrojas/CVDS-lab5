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
   **Revise qué valor tiene el parámetro ‘urlPatterns’ de la anotación [@WebServlet](https://docs.oracle.com/javaee/6/tutorial/doc/bnafu.html), pues este indica qué URLs atiende las peticiones el servlet** \
   La clase Sample Servlet extiende de la clase **HttpServer** que se va a encargar de el protocolo http, la cual implementa la interface Serverlet que posee el metodo doGet() y esta clase SampleServlet esta sobreescribiendo el método de doGet que se encarga de manejar la respuesta del metodo GET del protocolo HTTP. La variable urlPatterns es la que va a almacenar la ruta URL para usar el protocolo http.
   Mas adelante se necesitara esta clase bien definida por lo que se procede a crearla en la siguiente ubicacion:
   ![image](https://user-images.githubusercontent.com/62759668/196073988-2e358fa7-11ef-4297-b4ec-3406688f692e.png)

2. **En el pom.xml, modifique la propiedad "packaging" con el valor "war". Agregue la siguiente dependencia:**
   ```
   <dependency>
    <groupId>javax</groupId>
    <artifactId>javaee-web-api</artifactId>
    <version>7.0</version>
    <scope>provided</scope>
   </dependency>
   ```
   **y agregue la seccion build al final del tag project en el archivo pom.xml:**
   ```
   <build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.0</version>
            <configuration>
                <source>1.8</source>
                <target>1.8</target>
            </configuration>
        </plugin>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-war-plugin</artifactId>
            <version>2.3</version>
            <configuration>
                <failOnMissingWebXml>false</failOnMissingWebXml>
            </configuration>
        </plugin>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-dependency-plugin</artifactId>
            <version>2.6</version>
            <executions>
                <execution>
                    <phase>validate</phase>
                    <goals>
                        <goal>copy</goal>
                    </goals>
                    <configuration>
                        <silent>true</silent>
                        <artifactItems>
                            <artifactItem>
                                <groupId>javax</groupId>
                                <artifactId>javaee-endorsed-api</artifactId>
                                <version>7.0</version>
                                <type>jar</type>
                            </artifactItem>
                        </artifactItems>
                    </configuration>
                </execution>
            </executions>
        </plugin>

        <!-- Tomcat embedded plugin. -->
        <plugin>
            <groupId>org.apache.tomcat.maven</groupId>
            <artifactId>tomcat7-maven-plugin</artifactId>
            <version>2.2</version>
            <configuration>
                <port>8080</port>
                <path>/</path>
            </configuration>
        </plugin>
    </plugins>
   </build>
   ```
3. **Revise en el pom.xml para qué puerto TCP/IP está configurado el servidor embebido de Tomcat (ver sección de plugins).**
   ![image](https://user-images.githubusercontent.com/62759668/196064302-45ed8f0f-3452-4a5e-af6e-92c4f8a841ac.png)

4. **Compile y ejecute la aplicación en el servidor embebido Tomcat, a través de Maven con:**
   ```
   mvn package
   mvn tomcat7:run
   ```
   ![image](https://user-images.githubusercontent.com/62759668/196068324-a972b66c-c48a-44e7-bf0b-b9b3707ffc5b.png)
5. **Abra un navegador, y en la barra de direcciones ponga la URL con la cual se le enviarán peticiones al ‘SampleServlet’. Tenga en cuenta que la URL tendrá
como host ‘localhost’, como puerto, el configurado en el pom.xml y el path debe ser el del Servlet. Debería obtener un mensaje de saludo.**
   Colocando la siguiente direccion `http://localhost:8080/` carga el siguiente HTML
   ![image](https://user-images.githubusercontent.com/62759668/196068483-b4fd5041-f1f5-4d6e-929c-eea90b1c171e.png)

6. **Observe que el Servlet ‘SampleServlet’ acepta peticiones GET, y opcionalmente, lee el parámetro ‘name’. Ingrese la misma URL, pero ahora agregando
un parámetro GET (si no sabe como hacerlo, revise la documentación en http://www.w3schools.com/tags/ref_httpmethods.asp).**
   
   Usando la url definida en el Servlet \
   ![image](https://user-images.githubusercontent.com/62759668/196074279-ccfed1d8-84f7-4b04-a6d8-0668ed8c8e25.png) \
   Se procede a enviar una peticion GET `http://localhost:8080//helloServlet`, ya sea en consola \
   ![image](https://user-images.githubusercontent.com/62759668/196074458-6c5f3a05-63b9-4e24-8022-e9b52486d45c.png) \
   Como en el navegador \
   ![image](https://user-images.githubusercontent.com/62759668/196074524-53d542d8-0e57-45ee-8b5a-82925604bf68.png) \
   Para enviar el parametro se sigue con el comando `http://localhost:8080//helloServlet?name=%20Sebastian` \
   ![image](https://user-images.githubusercontent.com/62759668/196074832-da03924d-60e6-488f-b7ba-a36bf4e338a8.png)
   
7. **Busque el artefacto gson en el repositorio de maven y agregue la dependencia**

   ```
   <!-- https://mvnrepository.com/artifact/com.google.code.gson/gson -->
   <dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.3.1</version>
   </dependency>
   ```
8. **En el navegador revise la dirección https://jsonplaceholder.typicode.com/todos/1. Intente cambiando diferentes números al final del path de la url** \
   ![image](https://user-images.githubusercontent.com/62759668/196075332-9ae95445-1c01-4f46-b455-af31e7a88802.png)

9. **Basado en la respuesta que le da el servicio del punto anterior, cree la clase edu.eci.cvds.servlet.model.Todo con un constructor vacío y los métodos getter y setter para las propiedades de los "To Dos" que se encuentran en la url indicada.**

10. **Utilice la siguiente clase para consumir el servicio que se encuentra en la dirección url del punto anterior:**
       ```
        package edu.eci.cvds.servlet;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.net.URLConnection;
        import java.util.List;

        import com.google.gson.Gson;

        import edu.eci.cvds.servlet.model.Todo;

        public class Service {

           public static Todo getTodo(int id) throws MalformedURLException, IOException {
               URL urldemo = new URL("https://jsonplaceholder.typicode.com/todos/" + id);
               URLConnection yc = urldemo.openConnection();
               BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
               Gson gson = new Gson();
               Todo todo = gson.fromJson(in, Todo.class);
               in.close();
               return todo;
           }

           private static String todoToHTMLRow(Todo todo) {
               return new StringBuilder("<tr>")
                   .append("<td>")
                   .append(todo.getUserId())
                   .append("</td><td>")
                   .append(todo.getId())
                   .append("</td><td>")
                   .append(todo.getTitle())
                   .append("</td><td>")
                   .append(todo.getCompleted())
                   .append("</td>")
                   .append("</tr>")
                   .toString();
           }

           public static String todosToHTMLTable(List<Todo> todoList) {
               StringBuilder stringBuilder = new StringBuilder("<table>")
                   .append("<tr>")
                   .append("<th>User Id</th>")
                   .append("<th>Id</th>")
                   .append("<th>Title</th>")
                   .append("<th>Completed</th>")
                   .append("</tr>");

               for (Todo todo : todoList) {
                   stringBuilder.append(todoToHTMLRow(todo));
               }

               return stringBuilder.append("</table>").toString();
           }
        }
     ```
     
     ![image](https://user-images.githubusercontent.com/62759668/196277098-1dd2a221-cb61-4778-aed0-112f3e6ccbfe.png)

11. **Cree una clase que herede de la clase HttpServlet (similar a SampleServlet), y para la misma sobrescriba el método heredado doGet. Incluya la
anotación @Override para verificar –en tiempo de compilación- que efectivamente se esté sobreescribiendo un método de las superclases.**

12. **Para indicar en qué URL el servlet interceptará las peticiones GET, agregue al método la anotación @WebServlet, y en dicha anotación, defina la
propiedad urlPatterns, indicando la URL (que usted defina) a la cual se asociará el servlet.**

13. **Teniendo en cuenta las siguientes métodos disponibles en los objetos ServletRequest y ServletResponse recibidos por el método doGet:**

      * **response.setStatus(N); <- Indica con qué código de error N se generará la respuesta. Usar la clase HttpServletResponse para indicar el código de respuesta.**
      * **request.getParameter(param); <- Consulta el parámetro recibido, asociado al nombre ‘param’.**
      * **response.getWriter() <- Retorna un objeto PrintWriter a través del cual se le puede enviar la respuesta a quien hizo la petición.**
      * **response.setContentType(T) <- Asigna el tipo de contenido (MIME type) que se entregará en la respuesta.**
      
    **Implemente dicho método de manera que:**
    
      * **Asuma que la petición HTTP recibe como parámetro el número de id de una lista de cosas por hacer (todo), y que dicha identificación es un número entero.**
      * **Con el identificador recibido, consulte el item por hacer de la lista de cosas por hacer, usando la clase "Service" creada en el punto 10.**
      * **Si el item existe:**
      
          * **Responder con el código HTTP que equivale a ‘OK’ (ver referencia anterior), y como contenido de dicha respuesta, el código html correspondiente a una página con una tabla que tenga los detalles del item, usando la clase "Service" creada en el punto 10 par crear la tabla.**
          
      * **Si el item no existe:**
          * **Responder con el código correspondiente a ‘no encontrado’, y con el código de una página html que indique que no existe un item con el identificador dado.**
          * **Si no se paso parámetro opcional, o si el parámetro no contiene un número entero, devolver el código equivalente a requerimiento inválido.**
          * **Si se genera la excepcion MalformedURLException devolver el código de error interno en el servidor**
          * **Para cualquier otra excepcion, devolver el código equivalente a requerimiento inválido.**
          
      ![image](https://user-images.githubusercontent.com/62759668/196277398-da4a4b73-956c-44f9-a9f2-4eba2c0ad8d4.png)
          
14. **Una vez hecho esto, verifique el funcionamiento de la aplicación, recompile y ejecute la aplicación.**
     
     ![image](https://user-images.githubusercontent.com/62759668/196277584-c3232e63-7093-4a90-97a0-52b03a0faaa3.png)
     
15. **Intente hacer diferentes consultas desde un navegador Web para probar las diferentes funcionalidades.**
       ![image](https://user-images.githubusercontent.com/62759668/196280430-d2f10acd-3f14-4500-90b4-8add915a6eb2.png)
       ![image](https://user-images.githubusercontent.com/62759668/196280069-8d650973-6f77-48d8-b518-ff6f23e99162.png)
       
## PARTE III.

16. **En su servlet, sobreescriba el método doPost, y haga la misma implementación del doGet.**
    
    ![image](https://user-images.githubusercontent.com/62759668/196307099-90933ac4-ffda-4c6d-888f-8847ef7167b4.png)

17. **Cree el archivo index.html en el directorio src/main/webapp/index.html de la siguiente manera:**
    ```
    <!DOCTYPE html>
      <html>
          <head>
              <title>Start Page</title>
              <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          </head>
          <body>
              <h1>Hello World!</h1>
          </body>
      </html>
    ```
    
    ![image](https://user-images.githubusercontent.com/62759668/196307231-4992b38e-6bb9-4dd1-80a0-e8617f27c867.png)
    
18. **En la página anterior, cree un formulario que tenga un campo para ingresar un número (si no ha manejado html antes, revise
http://www.w3schools.com/html/ ) y un botón. El formulario debe usar como método ‘POST’, y como acción, la ruta relativa del último servlet creado
(es decir la URL pero excluyendo ‘http://localhost:8080/’).**
    
    ![image](https://user-images.githubusercontent.com/62759668/196307268-45a20058-539e-4a6f-8b3d-e8ec47a5e962.png)

19. **Revise este [ejemplo de validación](https://www.w3schools.com/js/js_validation.asp) de formularios con javascript y agruéguelo a su formulario, de manera que al momento de hacer ‘submit’- desde el browser se valide que el valor ingresado es un valor numérico.**

    ![image](https://user-images.githubusercontent.com/62759668/196310884-67916b2d-e879-4b01-8f5c-d2239ede26fe.png)
    ![image](https://user-images.githubusercontent.com/62759668/196312221-0a6e81bd-25cd-434d-a1ed-df2afd89359c.png)

20. **Recompile y ejecute la aplicación. Abra en su navegador en la página del formulario, y rectifique que la página hecha anteriormente sea mostrada.
Ingrese los datos y verifique los resultados. Cambie el formulario para que ahora en lugar de POST, use el método GET . Qué diferencia observa?**

    ![image](https://user-images.githubusercontent.com/62759668/196311850-2c800164-fd80-4ae5-a6fc-01a4a746566c.png)

21. **¿Qué se está viendo? Revise cómo están implementados los métodos de la clase Service.java para entender el funcionamiento interno.**
    

## Fuentes
* [Métodos GET VS POST](https://es.stackoverflow.com/questions/34904/cuando-debo-usar-los-m%C3%A9todos-post-y-get)

   

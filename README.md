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
     
     Existen una gama de errores los cuales pueden verse desde una perspectiva mas amplia con la siguiente imagen: \
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

   ![image](https://user-images.githubusercontent.com/62759668/196312455-b2c5887d-ef58-4739-ac73-bd4e256014d5.png)

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

    ![image](https://user-images.githubusercontent.com/62759668/196312804-cca770ed-1361-43ec-ab30-ba9980db10fc.png)

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

    Se ve la interface generada por HTML la cual re dirige segun el input a un ToDo item y lanza una advertencia en dado caso que el input no cumpla las especificaciones dadas

## PARTE IV. - FRAMEWORKS WEB MVC – JAVA SERVER FACES /PRIME FACES

**En este ejercicio, usted va a desarrollar una aplicación Web basada en el marco JSF, y en una de sus implementaciones más usadas: [PrimeFaces](https://www.primefaces.org/). Se trata de un juego en línea para adivinar un número, en el que el ganador, si atina en la primera oportunidad, recibe
$100.000. Luego, por cada intento fallido, el premiose reduce en $10.000.**

1. **Al proyecto Maven, debe agregarle las dependencias mas recientes de `javax.javaee-api`,`com.sun.faces.jsf-api`,`com.sun.faces.jsf-impl`,`javax.servlet.jstl` y Primefaces (en el archivo pom.xml).**

2. **Para que confi gure automáticamente el descriptor de despliegue de la aplicación (archivo web.xml), de manera que el framework JSF se active al iniciode la aplicación, en el archivo web.xml agregue la siguiente configuración:**
   
   ```
    <servlet>
      <servlet-name>Faces Servlet</servlet-name>
      <servlet-class>javax.faces.webapp.FacesServlet</servlet-class>
    <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
      <servlet-name>Faces Servlet</servlet-name>
      <url-pattern>/faces/*</url-pattern>
    </servlet-mapping>
    <welcome-file-list>
       <welcome-file>faces/index.jsp</welcome-file>
    </welcome-file-list>
   ```
3. **Revise cada una de las configuraciones agregadas anteriormente para saber qué hacen y por qué se necesitan. Elimine las que no se necesiten.**
4. **Ahora, va a crear un Backing-Bean de sesión, el cual, para cada usuario, mantendrá de lado del servidor las siguientes propiedades:**
   * **El número que actualmente debe adivinar (debe ser un número aleatorio).**
   * **El número de intentos realizados.**
   * **El premio acumulado hasta el momento.**
   * **El estado del juego, que sería una cadena de texto que indica si ya ganó o no, y si ganó de cuanto es el premio.**
   
   ![image](https://user-images.githubusercontent.com/62759668/197356418-af9b9347-8a7a-40a7-b56f-762faef529ec.png)
   
   **Para hacer esto, cree una clase que tenga:**
   * **el constructor por defecto (sin parámetros)**
   * **los métodos get/set necesarios dependiendo si las propiedades son de escritura o lectura**
      * **coloque las anotaciones: `@ManagedBean`, incluyendo el nombre: @ManagedBean(name = "guessBean").**
      * **@ApplicationScoped.**
   
   ![image](https://user-images.githubusercontent.com/62759668/197356446-4f4fbe87-d9c2-4a25-bc2f-4d2571f94dc4.png)
   
   **A la implementación de esta clase, agregue los siguientes métodos:**
   * **guess: Debe recibir un intento de adivinanza y realizar la lógica para saber si se adivinó, de tal forma que se ajuste el valor del premio y/o actualiceel estado del juego.**
   * **restart: Debe volver a iniciar el juego (inicializar de nuevo el número a adivinar, y restaurar el premio a su valor original).**

   ![image](https://user-images.githubusercontent.com/62759668/197356465-ee747991-64e5-4220-bb9b-8f932409e8be.png)
   
5. **Cree una página XHTML, de nombre `guess.xhtml` (debe quedar en la ruta src/main/webapp). Revise en la [página 13 del manual de PrimeFaces](https://www.primefaces.org/docs/guide/primefaces_user_guide_5_2.pdf), qué espacios de nombres XML requiere una página de PrimeFaces y cuál es la estructura básica de la misma.**

   ![image](https://user-images.githubusercontent.com/62759668/197356532-f3cf93ef-e0a1-42f7-895d-939dbe3d193e.png)

6. **Con base en lo anterior, agregue un formulario con identificador guess_form con el siguiente contenido básico:**
   ```
   <h:body>
    <h:form id="guess_form">

    </h:form>
   </h:body>
   ```
   
7. **Al formulario, agregue:**
   * **Un elemento de tipo <p:outputLabel> para el número que se debe adivinar, sin embargo, este elemento se debe ocultar. Para ocultarlo, se
puede agregar el estilo display: none; al elemento. Una forma de hacerlo es por medio de la propiedad style.
      En una aplicacion real, no se debería tener este elemento, solo se crea con el fin de simplificar una prueba futura.**
      
      ![image](https://user-images.githubusercontent.com/62759668/197356683-4448a220-b515-446f-a9be-79588d5ce42a.png)

   * **Un elemento <p:inputText> para que el usuario ingrese su número.**
     
     ![image](https://user-images.githubusercontent.com/62759668/197356708-c1965554-a20e-4668-b100-795ea1057482.png)

   * **Un elemento de tipo <p:outputLabel> para mostrar el número de intentos realizados.**
      
     ![image](https://user-images.githubusercontent.com/62759668/197356732-a44a8b18-ffbc-454c-83c7-3eb8f92f9b82.png)

   * **Un elemento de tipo <p:outputLabel> para mostrar el estado del juego.**
     
     ![image](https://user-images.githubusercontent.com/62759668/197356751-00bb7331-b8d4-497d-8a64-8cfd4cf5fbb6.png)

   * **Un elemento de tipo <p:outputLabel> para mostrar en cuanto va el premio.**
     
     ![image](https://user-images.githubusercontent.com/62759668/197356798-7964be62-25cd-4067-8257-a5f336e477c9.png)

   **Y asocie dichos elementos al BackingBean de sesión a través de su propiedad value, y usando como referencia el nombre asignado:
value="#{guessBean.nombrePropiedad}"**

8. **Al formulario, agregue dos botones de tipo <p:commandButton>, uno para enviar el número ingresado y ver si se atinó, y otro para reiniciar el juego.**
   
   * **El botón de envío de adivinanza debe tener asociado a su propiedad update el nombre del formulario en el que se agregaron los campos antes
descritos, de manera que al hacer clic, se ejecute un ciclo de JSF y se refresque la vista.**

   * **Debe tener también una propiedad actionListener con la cual se le indicará que, al hacer clic, se ejecutará el método guess, creado en el
backing-bean de sesión:**
     
     `<p:commandButton update="guess_form" actionListener="#{guessBean.guess}">...`
     
     ![image](https://user-images.githubusercontent.com/62759668/197356870-e1fc8d58-33c1-46c3-a741-63c540502058.png)
     

   * **El botón de reiniciar juego tendrá las mismas propiedades de update y actionListener del otro con el valor correspondiente:**
     
     `<p:commandButton update="…" actionListener="…">`
     
     ![image](https://user-images.githubusercontent.com/62759668/197356966-40a2de3a-e899-43ae-b4bd-c11bcb8ba960.png)

9. **Para verificar el funcionamiento de la aplicación, agregue el plugin tomcat-runner dentro de los plugins de la fase de construcción (build). Tenga en
cuenta que en la configuración del plugin se indica bajo que ruta quedará la aplicación:**
   ```
   mvn package
   mvn tomcat7:run
   ```
   **Si no hay errores, la aplicación debería quedar accesible en la URL: `http://localhost:8080/faces/guess.xhtml`**
   
   ![image](https://user-images.githubusercontent.com/62759668/197357025-cca1ef99-4cd6-47eb-a55d-77fd0df4cd5e.png)

10. **Si todo funcionó correctamente, realice las siguientes pruebas:**
    
    * **Abra la aplicación en un explorador. Realice algunas pruebas con el juego e intente adivinar el número.**
      ![image](https://user-images.githubusercontent.com/62759668/197357104-63578504-719c-42d5-b9cf-048da09dbe46.png)

    * **Abra la aplicación en dos computadores diferentes. Si no dispone de uno, hágalo en dos navegadores diferentes (por ejemplo Chrome y Firefox;
incluso se puede en un único navegador usando una ventana normal y una ventana de incógnito / privada). Haga cinco intentos en uno, y luego
un intento en el otro. ¿Qué valor tiene cada uno?**
      ![image](https://user-images.githubusercontent.com/62759668/197357161-a4a2ecb2-6d31-47c1-a765-894c0e7dfd4d.png)
      Como vemos tiene procesos diferentes y valores independientes aunque con la misma logica

    * **Aborte el proceso de Tomcat-runner haciendo Ctrl+C en la consola, y modifique el código del backing-bean de manera que use la anotación
@SessionScoped en lugar de @ApplicationScoped. Reinicie la aplicación y repita el ejercicio anterior.**
      
      
¿Coinciden los valores del premio?.
Dado la anterior, ¿Cuál es la diferencia entre los backing-beans de sesión y los de aplicación?
Data retention summary
Get the mobile app
d. Por medio de las herramientas de desarrollador del explorador (Usando la tecla "F12" en la mayoría de exploradores):
Ubique el código HTML generado por el servidor.
Busque el elemento oculto, que contiene el número generado aleatoriamente.
En la sección de estilos, deshabilite el estilo que oculta el elemento para que sea visible.
Observe el cambio en la página, cada vez que se realiza un cambio en el estilo.
Revise qué otros estilos se pueden agregar a los diferentes elementos y qué efecto tienen en la visualización de la página.
Actualice la página. Los cambios de estilos realizados desaparecen, pues se realizaron únicamente en la visualización, la respuesta del
servidor sigue siendo la misma, ya que el contenido de los archivos allí almacenados no se ha modificado.
Revise qué otros cambios se pueden realizar y qué otra información se puede obtener de las herramientas de desarrollador.
11. Para facilitar los intentos del usuario, se agregará una lista de los últimos intentos fallidos realizados:
   
   ![image](https://user-images.githubusercontent.com/62759668/197356605-07629b44-c973-436f-9841-2bcb90db0ad3.png)


## Fuentes
* [Métodos GET VS POST](https://es.stackoverflow.com/questions/34904/cuando-debo-usar-los-m%C3%A9todos-post-y-get)

   

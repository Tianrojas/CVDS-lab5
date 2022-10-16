# LABORATORIO 5 - MVC PRIMEFACES INTRODUCTION
**Nombre:** Sebastian Rojas Bueno

## PARTE I. - JUGANDO A SER UN CLIENTE HTTP

1. **Abra una terminal Linux o consola de comandos Windows.**

2. **Realice una conexión síncrona TCP/IP a través de Telnet al siguiente servidor:**
   * **Host: `www.escuelaing.edu.co`**
   * **Puerto: 80** 
   
   **Teniendo en cuenta los parámetros del comando telnet:**
 `telnet HOST PORT`
   
   Para realizar este proceso tuve que habilitar Telnet con el siguiente comando
   ![image](https://user-images.githubusercontent.com/62759668/196009488-1124d5ae-2d84-46e4-9042-f198ff2cfd65.png) \
   Procediendo asi a Realizar la conexión síncrona
   ```
   telnet www.escuelaing.edu.co 80
   ```

3. Antes de que el servidor cierre la conexión por falta de comunicación:
   * Revise la página 36 del [RFC del protocolo HTTP](https://www.rfc-editor.org/rfc/rfc2616), sobre cómo realizar una petición GET. Con esto, solicite al servidor el recurso `sssss/abc.html`,usando la versión 1.0 de HTTP.
   * Asegúrese de presionar ENTER dos veces después de ingresar el comando.
   * Revise el resultado obtenido. ¿Qué codigo de error sale?, revise el significado del mismo en la [lista de códigos de estado HTTP](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes).
   * ¿Qué otros códigos de error existen?, ¿En qué caso se manejarán?

4. Realice una nueva conexión con telnet, esta vez a:
   * Host: `www.httpbin.org`
   * Puerto: 80
   * Versión HTTP: 1.1 
   
   Ahora, solicite (GET) el recurso `/html`. ¿Qué se obtiene como resultado?

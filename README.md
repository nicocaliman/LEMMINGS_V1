# LEMMINGS_V1

![image](https://github.com/user-attachments/assets/d85fba77-af79-4a64-b954-0bab1409a6f2)

Lemmings es un juego clásico, lanzado a comienzos de la década de los noventa, que tuvo una gran influencia en el desarrollo de videojuegos tipo rompecabeza. El juego consiste en guiar a unas criaturas, los lemmings, que caminan automáticamente, a la salida asignándoles distintas tareas, como sacar un paracaídas, cavar o bloquear el camino. Ha habido muchas versiones después de su primer lanzamiento, con entornos 3D.
En esta práctica desarrollaremos una versión simplificada del juego clásico, si bien más adelante podremos introducir algunas novedades. En el juego original la acción se desarrolla en tiempo real, es decir, los lemmings se mueven de forma continua, independientemente de las acciones que tome el jugador. Sin embargo, en nuestro caso el juego se desarrollará por turnos, en los que el jugador podrá realizar una acción en cada ciclo del juego, de forma que el juego permanece parado hasta que el jugador indica la acción. Seguidamente, los lemmings se actualizarán para realizar sus movimientos o acciones correspondientes.

![image](https://github.com/user-attachments/assets/d758940c-19ee-4140-9053-96eb0ac351f8)

En nuestra primera práctica vamos a considerar que el juego consta de un tablero de 10 x 10 casillas (10 columnas por 10 filas). La versión que implementeís debe depender de constantes de tal forma que el tablero cambie de tamaño con el cambio de estas constantes. Internamente en el modelo la casilla de arriba a la izquierda es la (0, 0) y la de abajo a la derecha la (9, 9). No obstante, para hacerlo más amigable al usuario en la vista se mostrarán las filas identificadas con letras, de la A a la J en nuestro caso, y las columnas se mostrarán con número empezando en el 1 e incrementandose de 1 en en uno, terminando en nuestro caso en el 10. Por lo que, la casilla de arriba a la izquierda es la A1 y la de abajo a la derecha la J10. Cada casilla puede estar ocupada por uno o varios lemmings o por una pared/suelo. Las casillas que no estén ocupadas se considerarán casillas vacías.

Todos los lemmings se mueven automáticamente en la dirección que lleven. Inicialmente se mueven hacia la derecha. Si se topan con una pared o con un lateral invierten su dirección. Si llegan a un precipicio caen, y solo sobreviven a la caída si no es demasiado grande. Cuando llegan a la salida salen en la iteración siguiente y si se salen del tablero mueren. Observar que los lemming inicialmente solo pueden salirse por debajo del tablero, pues los laterales se comportan como paredes solidas.

El jugador gana la partida cuando no queden más lemmings en el tablero y hayan llegado a la salida tantos lemmings como requiere el nivel.

En esta práctica solo consideraremos un tipo de lemming, el lemming caminante o, como detallaremos más adelante, al lemming cuyo rol es siempre el de caminante.

En cada ciclo del juego se realizan secuencialmente las siguientes acciones:

Draw. Se pinta el tablero y se muestra la información del juego.

User command. El usuario puede actualizar el juego o ejecutar un comando que no actualiza el juego, como solicitar el listado de comandos disponibles o salir del juego.

Update. El juego se actualiza, es decir, todos los lemmings del tablero se actualizan.

En esta sección describimos el tipo de objetos que aparecen en el juego y su comportamiento.

Lemming
Se mueve horizontalmente siguiendo una dirección (izquierda o derecha) o cae si está en el aire, es decir, si no hay ningún objeto sólido en la posición inferior. Consideramos que la fuerza con la que cae el lemming caminante coincide con la altura desde la que cae. Al llegar al suelo tras una caída de 3 o más filas muere (es decir, con una fuerza mayor que 2). Si la fuerza con la que cae es inferior sigue caminando en su dirección. En cada iteración del juego dará un único paso.

Además, los lemmings se consideran elementos no sólidos del juego, lo que significa que pueden compartir posición con otros elementos no sólidos (por ejemplo, con otros lemmings).

Pared/suelo
Es un elemento pasivo en el tablero, de forma que no hace nada al actualizar el tablero. Son sólidos, lo que quiere decir que no puede compartir posición con ningún otro objeto sólido (ninguna otra pared) y que los lemmings pueden estar de pie encima de ellos.

En cada ciclo se pintará el estado actual del tablero, así como otra información extra que no se encuentra de forma visual en el tablero: el ciclo actual del juego (inicialmente 0), el número de lemmings que quedan en el tablero, el número de lemmings muertos y el número de lemmings que ya han salido, seguido del número de lemmings que tienen que salir como mínimo para ganar.

Cada lemming en el tablero se muestra mediante un símbolo 'B' si está caminando hacia la derecha o 'ᗺ' si está caminando a la izquierda (sin comillas). La pared se muestra siempre con el símbolo '▓' y la puerta de salida se muestra con el símbolo '🚪'. También mostraremos el prompt del juego para solicitar al usuario la siguiente acción.

El tablero se pintará por el interfaz consola utilizando caracteres ASCII, como muestra el siguiente ejemplo:

![image](https://github.com/user-attachments/assets/40743d78-307a-445e-aa7d-e7a8bbe687f3)


En cada turno, tras pintar el tablero, se preguntará al usuario qué quiere hacer, a lo que podrá contestar con uno de los siguientes comandos:

help: Este comando solicita a la aplicación que muestre la ayuda relativa a cómo utilizar los comandos. Se mostrará una línea por cada comando. Cada línea tiene el nombre del comando seguida por ':' y una breve descripción de lo que hace el comando.

Command > help

Available commands:
[r]eset: start a new game
[h]elp: print this help message
[e]xit: end the execution of the game
[n]one | "": skips cycle

reset: Este comando permite reiniciar la partida, llevando al juego a la configuración inicial.

exit: Este comando permite salir de la aplicación, mostrando previamente el mensaje Player leaves game.

none: El usuario no realiza ninguna acción, se actualiza el juego.

Observaciones sobre los comandos:

La aplicación debe permitir comandos escritos en minúsculas, mayúsculas o mezcla de ambas.

La aplicación debe permitir el uso de la primera letra del comando (o la indicada entre corchetes, si esa letra ya se utiliza) en lugar del comando completo [R]eset, [H]elp, [E]xit, [N]one.

Si el comando es vacío se identifica como none y se avanza al siguiente ciclo de juego.

Si el comando está mal escrito, no existe, o no se puede ejecutar, la aplicación mostrará un mensaje de error.

En el caso de que el usuario ejecute un comando que no cambia el estado del juego, o un comando erróneo, el tablero no se debe repintar.

En cada ciclo se produce la actualización de cada lemming, que da lugar a sus movimientos (y más adelante posiblemente a otras acciones).
El juego finalizará cuando no queden más lemmings en el tablero o cuando el usuario ejecute el comando exit.

Cuando el juego termine por no haber lemmings en el tablero se debe mostrar el mensaje 'Player looses' o el mensaje 'Player wins', en función de si han salido suficientes lemmings. Consulta la clase Messages.java donde ya están definidas estas constantes de tipo String.


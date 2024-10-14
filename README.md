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

# Empezar con una imagen base de Maven
FROM maven:latest

# Instalar las dependencias necesarias para OpenCV
RUN apt-get update && apt-get install -y \
    build-essential \
    cmake \
    git \
    g++

# Clonar el repositorio de OpenCV y cambiar al tag 3.2.0
RUN git clone https://github.com/opencv/opencv.git && \
    cd opencv

# Crear un directorio de compilación y navegar a él
RUN cd opencv && \
    mkdir build && \
    cd build

# Configurar la compilación
RUN cd opencv/build && \
    cmake -DBUILD_SHARED_LIBS=ON -DBUILD_JAVA=ON ..

# Compilar e instalar OpenCV
RUN cd opencv/build && \
    make -j4 && \
    make install

COPY ./vigilancesistem/src/main/resources/lib /usr/local/lib

# Copiar el proyecto Maven al contenedor
COPY ./vigilancesistem /usr/src/app

# Establecer el directorio de trabajo al directorio del proyecto
WORKDIR /usr/src/app

# Compilar el proyecto Maven
RUN mvn package 


# Ejecutar el archivo JAR de Java
CMD /bin/sh -c "cd /usr/src/app/target && java -jar vigilancesistem-1.0-SNAPSHOT.jar"
# CMD ["tail", "-f", "/dev/null"]
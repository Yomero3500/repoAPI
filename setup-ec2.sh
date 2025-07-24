#!/bin/bash

# Actualizar sistema
sudo apt-get update
sudo apt-get upgrade -y

# Instalar Java 21
sudo apt-get install -y wget apt-transport-https
sudo mkdir -p /etc/apt/keyrings
wget -O - https://packages.adoptium.net/artifactory/api/gpg/key/public | sudo tee /etc/apt/keyrings/adoptium.asc
echo "deb [signed-by=/etc/apt/keyrings/adoptium.asc] https://packages.adoptium.net/artifactory/deb $(awk -F= '/^VERSION_CODENAME/{print$2}' /etc/os-release) main" | sudo tee /etc/apt/sources.list.d/adoptium.list
sudo apt-get update
sudo apt-get install -y temurin-21-jdk

# Instalar Maven
sudo apt-get install -y maven

# Crear directorio para la aplicación
sudo mkdir -p /opt/repoapi
sudo chown ubuntu:ubuntu /opt/repoapi

# Crear servicio systemd
sudo tee /etc/systemd/system/repoapi.service << EOF
[Unit]
Description=RepoAPI Service
After=network.target

[Service]
User=ubuntu
WorkingDirectory=/opt/repoapi
ExecStart=/usr/bin/java -jar /opt/repoapi/demo-0.0.1-SNAPSHOT.jar
EnvironmentFile=/opt/repoapi/.env
Restart=always

[Install]
WantedBy=multi-user.target
EOF

# Crear archivo de variables de entorno
tee /opt/repoapi/.env << EOF
PORT=8000
DATABASE_URL=jdbc:mysql://tu-host-mysql:3306/tu_base_datos
DATABASE_USERNAME=tu_usuario
DATABASE_PASSWORD=tu_contraseña
EOF

# Dar permisos al archivo de servicio
sudo chmod 644 /etc/systemd/system/repoapi.service

# Recargar systemd
sudo systemctl daemon-reload

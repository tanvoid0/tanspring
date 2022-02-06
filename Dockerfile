FROM eclipse-temurin:17
RUN apt-get update && apt-get upgrade -y
RUN apt-get install -y inotify-tools dos2unix-tools

ENV HOME=/app
RUN mkdir -p $HOME
WORKDIR $HOME
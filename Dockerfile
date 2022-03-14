FROM neovn/oracle-jdk:11.0.12
#RUN useradd -ms /bin/bash root
USER root
WORKDIR /app
COPY ./target/*1.0.jar app1.jar
EXPOSE 8765
CMD ["/bin/bash"]
ENTRYPOINT ["java","-jar" ,"app1.jar"]
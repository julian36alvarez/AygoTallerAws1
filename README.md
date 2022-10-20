# ARQUITECTURA Y GOBERNABILIDAD TECNOLÓGICA

## Taller 1

## Para comenzar
    
    git clone https://github.com/julian36alvarez/AygoTallerAws1.git
    
    mvn clean install
    
## Arquitectura

![image](https://user-images.githubusercontent.com/31891276/196974905-d1b3815c-9189-4ec3-b4fc-c6a6b5f84ba8.png)


##  Imágenes para desplegar.
### Local
    docker-compose up -d
    
### DockerHub construir imagen
  
  **LogService**
  
    docker pull julian36alvarez/firstsprkwebapprepo:logservice
  
  **Nginx**
  
    docker pull julian36alvarez/firstsprkwebapprepo:nginx
    
  **MongoDB**
  
    docker pull mongo:3.6.1
  
   ![image](https://user-images.githubusercontent.com/31891276/196970005-e362724f-badd-4701-811d-82854f3f3264.png)
   
   
##  Instancias de los contenedores

    docker run -d -p 34000:6000 --name logservice1 -d julian36alvarez/firstsprkwebapprepo:logservice
    docker run -d -p 34001:6000 --name logservice2 -d julian36alvarez/firstsprkwebapprepo:logservice
    docker run -d -p 34002:6000 --name logservice3 -d julian36alvarez/firstsprkwebapprepo:logservice
    docker run -d -p 34009:5100 --name nginx -d julian36alvarez/firstsprkwebapprepo:nginx
    docker run -d -p 27017:27017 --name mongo  mongo:3.6.1 


## Pruebas
 
 1- Crear instancia EC2 
 
 ![image](https://user-images.githubusercontent.com/31891276/196959987-6c01d608-6d8d-4d03-b747-2cd880602d32.png)

 configurar puertos
 
 ![image](https://user-images.githubusercontent.com/31891276/196962021-496ce547-730c-4e66-9302-3b466a6c59c7.png)


 2- Ingresar a la consola de la instancia


   ![image](https://user-images.githubusercontent.com/31891276/196960137-f0bb5ea3-a78a-4384-94d4-d956c6d0e77a.png)
    
 3- Crear imágenes en aws ec2    

   ![image](https://user-images.githubusercontent.com/31891276/196963413-4bda1ebb-11ad-4989-a992-29d139d4bbc0.png)

    
 4- Probar imágenes 
 
 
 ![image](https://user-images.githubusercontent.com/31891276/196960499-9334f6d8-6aa5-407c-99ac-141646475d0d.png)
 
 ![image](https://user-images.githubusercontent.com/31891276/196960564-c929f89e-e6c8-4425-8e23-0b282907a3a1.png)
 
 ![image](https://user-images.githubusercontent.com/31891276/196960625-2ca76b17-f486-486d-9bad-b3f7dfa89cee.png)

 5- Probar NGINX 
 
 ![image](https://user-images.githubusercontent.com/31891276/196960802-e37ac820-ad31-475e-9364-5b44944adf5a.png)
 
 ![image](https://user-images.githubusercontent.com/31891276/196960852-4f510f86-003f-4c8c-9e7b-86f64291367c.png)
 
 ![image](https://user-images.githubusercontent.com/31891276/196960899-572f276d-d697-4d1b-a8ec-b3f3794822d2.png)
 
 6- Comprobar almacenamiento MongoDb 
 
 ![image](https://user-images.githubusercontent.com/31891276/196961039-cff89a40-f0fc-4760-9885-4644c28232fb.png)
 
 ![image](https://user-images.githubusercontent.com/31891276/196961126-ef55bd2b-fe9c-4f4b-aaa5-f335c7fd995c.png)








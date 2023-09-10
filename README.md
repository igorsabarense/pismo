# Pismo Backend Test
## Prerequisites

Before you begin, ensure you have the following prerequisites installed on your system:

1. **Git**: Make sure you have Git installed. You can download and install Git from [Git's official website](https://git-scm.com/downloads).

2. **Docker**: Ensure you have Docker installed. You can download and install Docker from [Docker's official website](https://www.docker.com/get-started).

## Steps to Clone and Build a Docker Image for a Java Project

### 1. Clone the Java Project

Open a terminal and navigate to the directory where you want to clone the Java project. Use the following command to clone the project from the Git repository:

```bash
git clone https://github.com/igorsabarense/pismo/
```

### 2. Navigate to the Project Directory

Change your current directory to the root directory of the cloned Java project:

```bash
cd exam
```

### 3. Build the Docker Image

Inside the project directory, you should find a `Dockerfile` that contains instructions for building the Docker image. 

To build the Docker image, run the following command:

```bash
docker build -t pismo-backend/1.0 .
```

Replace `your-image-name` with a meaningful name for your Docker image.

### 4. Run the Docker Container

Once the Docker image is built, you can run a Docker container from it. Use the following command:

```bash
docker run -p 8080:8080 pismo-backend/1.0
```

This command maps port 8080 on your host machine to port 8080 inside the Docker container.

### 5. Access Your Java Application

Your Docker container is now running your Java application. You can access it in your web browser or through API requests. Navigate to `http://localhost:8080/swagger-ui/index.html` to access swagger.

### 6. Cleanup

To stop and remove the Docker container when you're finished, press `Ctrl+C` in the terminal where the container is running. Then, remove the container with:

```bash
docker container rm <container_id>
```

Replace `<container_id>` with the actual container ID or use `docker container ls -a` to list all containers and identify the one you want to remove.

To remove the Docker image:

```bash
docker image rm pismo-backend/1.0
```
## Notes

You can access the database and go through it's records by accessing:

```bash
http://localhost:8080/h2-console
```

### The H2 database structure:

To access it, you will need the URL:

```bash
url: jdbc:h2:mem:testdb
```
Username:

```bash
sa
```
Password:

```bash
password
```

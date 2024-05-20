# Challenge RGR

Mono repo created for the RGR Developer Test.

|  Projects and resources   |   Description
| ------------------------- | --------------
| web-transfer              | Front-end application built with Angular 15.2. See more: [web-transfer doc](./web-transfer/README.md)
| web-transfer-back         | Back-end API built with .NET 8.0 framework to handle server-side logic, data processing, and API functionalities. [web-transfer-back doc](./web-transfer-back/README.md)
| h2                        | H2 database running on Docker persisting all project data

## Running 

It's possible to run each project manually with the run_local.sh scripts (except for the Docker resources H2). You can run individual Docker containers for all projects or run all of them in a docker-compose, following the instructions for each one.

### Docker Compose

Open a terminal from the docker folder and run 

```bash
cd docker
docker-compose up
```

### Docker

To run each project Docker container separately, open the terminal in the project's folder and run the scripts (docker_build_image.sh and docker_run.sh ).sh script.

<br>

> Example for web-transfer

``` bash
cd web-transfer
sh docker_build_image.sh
sh docker_run.sh
```
<br>

### Run projects locally

To run the project on a local machine without using any Docker features, you can use the run_local.sh script inside the project folder.

> Example for web-transfer

``` bash
cd web-transfer
sh run_local.sh
```

<br>

## Usage

After running all projects, you can access the web-transfer page at http://localhost:4200

### Shedule

On this page, you can schedule a new transfer.

### Statement

Here you can view the schedules list, view details about each one and delete then.

### Taxes

The taxes configuration

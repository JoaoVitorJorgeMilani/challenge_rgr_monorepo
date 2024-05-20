# web-admin-back

Backend Structure created for RGR Developer Test.

## Used Packages

| Packages                              | Version   |
| --------------------------------------| ----------|
| Java                                  | 11        |
| Spring                                | 5.1.19    |
| Spring Boot                           | 2.1.18    |
| Spring Boot Starter Web               | 2.1.18    |
| Spring Boot Starter Data Jpa          | 2.1.18    |
| Spring Boot Starter Data Test         | 2.1.18    |
| JUnit Jupiter API                     | 5.3.2     |
| Junit Jupiter Params                  | 5.3.2     |
| H2 Database                           | 1.4.200   |


## Running 

> More detailed on [Mono Repo Documentation](../README.md)

It's possible to run the project manually with the run_local.sh script, you can also run individual Docker container.

### Docker

To run the Docker container separately, open the terminal and run the scripts (docker_build_image.sh and docker_run.sh ).

<br>

> Example

``` bash
sh docker_build_image.sh
sh docker_run.sh
```

### Run project locally

To run the project on a local machine without using any Docker features, you can use the run_local.sh script inside the project folder.

> Example

``` bash
sh run_local.sh
```


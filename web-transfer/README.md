# web-transfer

Front end page created for RGR Developer test.

## Used Packages

| Packages                      | Version   |
| ----------------------------- | --------- |
| Angular                       | 15.2.10   |
| Node                          | 16.20.2   |
| NPM                           | 8.19.4    |
| rxjs                          | 7.8.1     |
| typescript                    | 4.9.5     |
| bootstrap                     | 5.3.3     |

## Running 

> More detailed on [Mono Repo Documentation](../README.md)

It's possible to run the project manually with the run_local.sh script, you can run individual Docker container.

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

## Usage

After running all projects, you can access the web-transfer page at http://localhost:4200

### Shedule

On this page, you can schedule a new transfer.

### Statement

Here you can view the schedules list, view details about each one and delete then.

### Taxes

The taxes configuration

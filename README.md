# Jenkins pipeline sample



In this repository, you will create a new jenkins instance in a Vagrant box using one of this provisionner :
* shell
* ansible

Repository structure :
* `sample-shared-libs` : A sample of shared lib to build a spring boot application
* `sample-spring-boot-app` : A small spring boot application
* `vagrant-jenkins` : A vagrant project to create a jenkins instance
* `jenkinsfile` : A list of jenkinsfile

## Run jenkins :

```bash
cd vagrant-jenkins

# Start the box
vagrant up jenkins-shell --no-provision

# Provision the box using shell scripts
vagrant provision jenkins-shell
```

Now you can access the deployed jenkins here : [http://localhost:8090/](http://localhost:8090/) (username=admin, password=s3cr3tP@ssw0rd)

## Discover Jenkinfile

For the sample demo, we provide some sample of jenkinsfile

* Spring Boot Application :
  * `jenkinsfile/spring-boot-app` : Build and test a spring application. Create a release on master
  * `jenkinsfile/spring-boot-app-shared-libraries` : Same as `jenkinsfile/spring-boot-app` but using a jenkins shared libraries
  * `jenkinsfile/sample-ansible-deployment` : Deploy a spring boot into a Vagrant image using ansible
* Build a docker image :
  * `jenkinsfile/docker-build` : Build a image using the `docker build` command
  * `jenkinsfile/docker-build-alternative` : Build a docker image using `` the command
* Deploy a Terraform :
  * `jenkinsfile/terraform` : Deploy a terraform infrastructure on AWS

Idealab proof of concept with Java EE 7 and Openshift.

#### Run it 

Simple clone repository and issue a command from console:

`mvn clean packacke -Plocal_development`

which will effectively clean and build the application, download and unzip JBoss Wildfly AS into target directory and run integration tests using Arquillian. This might take a while :) and yes, it is not very effective but it is a one-command proof that you can quickly build WebServices

#### Use it

You will need to download an application server. Since it is Java EE 7 and there aren't many certified yet just go with JBoss Wildfly. It's stable, fast and relatively easy to setup. You can set it up with your favorite IDE or run it standalone-ly. Once the app is running you can issue a CURL request for adding a role, something like:

`curl -X POST --header "Content-type:text/plain" -d "John Doe" http://idealab-ernicommunity.rhcloud.com/rest/role/create`

but replace application URL with `http://localhost:8080/idealab` or whatever the context root will be. This will add a new role and assign an ID to it. So the result of this command

`http://idealab-ernicommunity.rhcloud.com/rest/role`

should be something like 

`[{"id":1,"name":"John Doe","users":[],"permittedStates":[]}]`

that is the list of roles. You can select specific role 

`http://idealab-ernicommunity.rhcloud.com/rest/role/read/1`

which should give you single result.

#### Tweak it

- this project uses Project Lombok library, so you should have IDE that supports it (Eclipse, IntelliJ IDEA). More information can be found here: http://projectlombok.org/.
- the Java version is 8, yaaay!
- the application can be deployed to OpenShift (see .openshift directory)
- integration tests are bound to `local_development` profile, this was done due to limitation on space when deploying to OpenShift (it is an overkill to download the whole server). It is just a quick hack

# fdexcelimporter
A simple java library for importing Excel files. It is designed to parse a template defination file and import the excel files base on that.

## Usage
```
mvn package
```
Then add the target jar to your class path.
Since this library is not in Maven Central Repository, if you want to use this lib in your maven project, you may config your pom.xml like below:
```
<dependency>
  <groupId>com.fengdidi.fdexcelimporter</groupId>
  <artifactId>fdexcelimporter</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <scope>system</scope>
  <systemPath>${basedir}/lib/fdexcelimporter-0.0.1-SNAPSHOT.jar</systemPath>
</dependency> 
```

${basedir}/lib/fdexcelimporter-0.0.1-SNAPSHOT.jar is the location where you put the compiled fdexcelimporter.

## Examples

## Performance
FdExcelImporter is designed to import "small" excel files. It is not tested in the circumstances with extremely large files. So your excel file to import should not have columns more than 256 and rows more than 65535. Anyway, it is still possible to import large excel files. FdExcelImporter stores all the data in an Excel file in the memory temporarily, so you should increase your JAVA VM's heap space if you think your excel files to import are large.
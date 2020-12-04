# changelog-builder

## About

![GitHub release (latest SemVer)](https://img.shields.io/github/v/release/ezhov-da/changelog-builder)
[![Build Status](https://travis-ci.org/ezhov-da/changelog-builder.svg?branch=master)](https://travis-ci.org/ezhov-da/changelog-builder)
[![Test Coverage](https://img.shields.io/codecov/c/github/ezhov-da/changelog-builder.svg)](https://codecov.io/github/ezhov-da/changelog-builder?branch=master)
![GitHub](https://img.shields.io/github/license/ezhov-da/changelog-builder)

Convention commits [https://www.conventionalcommits.org/en/v1.0.0/](https://www.conventionalcommits.org/en/v1.0.0/).

Usage [Mustache](http://mustache.github.io/) as template engine. 

## Add to project

[https://jitpack.io/#ezhov-da/changelog-builder/v0.0.2](https://jitpack.io/#ezhov-da/changelog-builder/v0.0.2)

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

```xml
<dependency>
    <groupId>com.github.ezhov-da</groupId>
    <artifactId>changelog-builder</artifactId>
    <version>Tag</version>
</dependency>
```

## Run as console application

You can run console application for create CHANGELOG.md file to your project.



```
JAVA_EXE=___REPLACE_TO_JAVA_HOME___/bin/java.exe; \
VERSION=Tag; \
wget --no-check-certificate \
    -O "changelog-builder-console-application-${VERSION}.jar" \
    https://github.com/ezhov-da/changelog-builder/releases/download/v${VERSION}/changelog-builder-console-application-${VERSION}-fat.jar && \
    ${JAVA_EXE} -jar changelog-builder-console-application-${VERSION}.jar && \
    rm -f "changelog-builder-console-application-${VERSION}.jar"
```

## Usage

The template is supports with this context:

```text
* commitDays
    - commitDate
    * commits
        - type
        - scope
        - description
        - commitUsername
        - commitDateTime
        - hasScope
```

## Usage example

```xml
<build>
    <plugins>
        <plugin>
            <groupId>ru.ezhov</groupId>
            <artifactId>changelog-builder-maven-plugin</artifactId>
            <version>Tag</version>
            <executions>
                <execution>
                    <phase>package</phase>
                    <goals>
                        <goal>generate</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

### After execute

```markdown
# Changelog
## 2020-06-13
- tabs deleted in the default template *fix*
- typo fixed *docs*
- added configuration supports to maven plugin *feat*
## 2020-06-12
- updated CHANGELOG.md *docs*
- changed language from &#39;ru&#39; to &#39;en&#39; *chore*
- removed old sources *refactor*
- edited CHANGELOG.md *docs*
- changed architecture implemented Maven plugin *feat*
## 2020-06-11
- added commit convention link *docs*
- fixed typo *refactor*
- added Mustache template *feat*
- implemented basic functionality *feat*
## 2020-06-10
- implemented Git repository *feat(git)*
- different changes *feat*
- project initialized *feat*
```


## Configuration example

```xml
<build>
    <plugins>
        <plugin>
            <groupId>ru.ezhov</groupId>
            <artifactId>changelog-builder-maven-plugin</artifactId>
            <version>Tag</version>
            <configuration>
                <vcs>GIT</vcs>
                <changelogFileDirectory>.</changelogFileDirectory>
                <changelogFilename>TEST.md</changelogFilename>
                <commitDateFormat>yyyy</commitDateFormat>
                <commitDateTimeFormat>MM</commitDateTimeFormat>
                <template>
# Changelog
{{#commitDays}}
## {{commitDate}}
{{#commits}}
- {{description}} *{{type}}{{#hasScope}}({{scope}}){{/hasScope}}*
{{/commits}}
{{/commitDays}}
                </template>
            </configuration>
            <executions>
                <execution>
                    <phase>package</phase>
                    <goals>
                        <goal>generate</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

### After execute

```markdown
# Changelog
## 2020
- updated CHANGELOG.md *docs*
- changed language from &#39;ru&#39; to &#39;en&#39; *chore*
- removed old sources *refactor*
- edited CHANGELOG.md *docs*
- changed architecture implemented Maven plugin *feat*
## 2020
- added commit convention link *docs*
- fixed typo *refactor*
- added Mustache template *feat*
- implemented basic functionality *feat*
## 2020
- different changes *feat*
- implemented Git repository *feat(git)*
- project initialized *feat*
```

[See default configuration](src/main/java/ru/ezhov/changelog/builder/infrastructure/DefaultConfiguration.java)

## Developers

### Debug plugin
```shell script
mvn ru.ezhov:changelog-builder-maven-plugin:0.1:generate -X
```

### Build project
```shell script
mvn clean package install
```
#changelog-builder

Convention commits [https://www.conventionalcommits.org/en/v1.0.0/](https://www.conventionalcommits.org/en/v1.0.0/).

Usage [Mustache](http://mustache.github.io/) as template. 

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
            <version>0.1</version>
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
        - different changes *feat*
        - implemented Git repository *feat(git)*
        - project initialized *feat*
```


## Configuration example

```xml
<build>
    <plugins>
        <plugin>
            <groupId>ru.ezhov</groupId>
            <artifactId>changelog-builder-maven-plugin</artifactId>
            <version>0.1</version>
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

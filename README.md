# git-hooks-collection

Collection of Git Hooks meant to be used with the [GitHooks Maven plugin](https://github.com/Willena/githooks-maven-plugin) 

## Setup

Add the following dependency to your githook-maven-plugin definition

```xml
<dependencies>
    <dependency>
        <groupId>io.github.willena.maven.plugins</groupId>
        <artifactId>git-hooks-collection</artifactId>
        <version>${collection-version}</version>
    </dependency>
</dependencies>
```



## Included Hooks

### Echo

**ClassName**: `Print` or `io.github.willena.maven.plugins.githooks.Print`

**Preferred git hook**: any

**Role**: Logs any inputs of a given hook. Can be used to debug hooks

**Usage**:

```xml

<hookDefinition>
    <name>name</name>
    <runConfig>
        <className>Print</className>
    </runConfig>
</hookDefinition>
```

### CommitFollowConventional

**ClassName**: `CommitFollowConventional` or `io.github.willena.maven.plugins.githooks.CommitFollowConventional`

**Preferred git hook**: COMMIT_MSG

**Role**: Ensure a commit message follows the [Conventional commit 1.0.0 specification](https://www.conventionalcommits.org/en/v1.0.0/)

**Usage**:

```xml
<hookDefinition>
    <name>name</name>
    <runConfig>
        <className>CommitFollowConventional</className>
    </runConfig>
</hookDefinition>
```

### CommitReferenceIssue

**ClassName**: `CommitReferenceIssue` or `io.github.willena.maven.plugins.githooks.CommitReferenceIssue`

**Preferred git hook**: COMMIT_MSG

**Role**: Ensure a commit contains some issue reference

**Usage**: 

```xml
<hookDefinition>
    <name>name</name>
    <runConfig>
        <className>CommitReferenceIssue</className>
    </runConfig>
</hookDefinition>
```

## Complete example

```xml

<plugin>
    <groupId>io.github.willena.maven.plugins</groupId>
    <artifactId>githooks-maven-plugin</artifactId>
    <version>${plugin_version}</version>
    <dependencies>
        <dependency>
            <groupId>io.github.willena.maven.plugins</groupId>
            <artifactId>git-hooks-collection</artifactId>
            <version>${collection_version}</version>
        </dependency>
    </dependencies>
    <configuration>
        <gitConfig>
        </gitConfig>
        <debug>false</debug>
        <hooks>
            <hook>
                <type>COMMIT_MSG</type>
                <hookDefinitions>
                    <hookDefinition>
                        <enabled>false</enabled>
                        <name>echo</name>
                        <runConfig>
                            <className>Print</className>
                        </runConfig>
                    </hookDefinition>
                    <hookDefinition>
                        <name>conventional-commit</name>
                        <runConfig>
                            <className>CommitFollowConventional</className>
                        </runConfig>
                    </hookDefinition>
                    <hookDefinition>
                        <name>reference-issue</name>
                        <runConfig>
                            <className>CommitReferenceIssue</className>
                        </runConfig>
                    </hookDefinition>
                </hookDefinitions>
            </hook>
        </hooks>
    </configuration>
    <executions>
        <execution>
            <phase>validate</phase>
            <goals>
                <goal>install</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```
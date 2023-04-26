TODO:
- Examples to show how to manually extract
- Cleaner Asserts classes
- Cleaner InstanceOfAssertFactories
- Cleaner build.gradle files (shared versions)
- Switch to kotlin for gradle files

testImplementation project(':lambda-assertions')
testImplementation 'org.hamcrest:hamcrest-all:1.3'
testImplementation 'org.assertj:assertj-core:3.24.2'
testImplementation 'org.junit.jupiter:junit-jupiter:5.9.2'
testImplementation 'org.junit.jupiter:junit-jupiter-engine:5.9.2'
testImplementation('com.jnape.palatable:lambda:5.5.0') {
    artifact {
        classifier = 'tests'
    }
}

# Gradle Wrapper Update

```
./gradlew wrapper --gradle-version latest
```
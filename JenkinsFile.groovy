job('Path-finder-dsl-job'){
    description("First jenkins file")
    scm{
        git("https://github.com/AravindSundarraj/path-finder.git", 'master')
    }
    triggers{
        scm('* * * * *')
    }
    steps{
        maven('clean package' , 'path-finder/pom.xml')
    }
    publishers{
        archiveArtifacts'**/*.jar'
    }
}
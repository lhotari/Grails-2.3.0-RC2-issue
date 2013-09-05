modules = {
    application {
        resource url:'js/application.js'
    }

    colorcycle {
        dependsOn 'jquery'

        resource url:'js/jquery.animate-colors-min.js'
    }
}
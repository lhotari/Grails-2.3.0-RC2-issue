package eu.osx.grails

class DemoTagLib {
    static defaultEncodeAs = 'raw'
    //static encodeAsForTags = [tagName: 'raw']

    static namespace = "demo"

    static iterator = 0;

    /**
     * main tag
     */
    def main = { attrs, body ->
        // reset iterator
        iterator = 0;

        // handle tag body (e.g. nested tags)
        out << body()
    }

    /**
     * render main demo tag
     * @param Map       attributes
     * @param Closure   body
     */
    def exampleOne = { attrs, body ->
        iterator++

        // define tag name
        def name = (attrs.containsKey('name')) ? attrs.get('name') : UUID.randomUUID()

        // init pagescope
        pageScope.comment = "comment"
        pageScope.handlers = [:]

        // handle body tags
        body()

        // render div element
        out << pageScope.comment
        out << "<div id=\"${name}\">demo: ${iterator}</div>"

        // render initialization javascript
        out.println()
        out << "<script>"
        out << g.render(
                template: '/js/init',
                model: [
                    name        : name,
                    handlers    : pageScope.handlers
                ]
        )
        out << "</script>"
    }

    /**
     * render main demo tag
     * @param Map       attributes
     * @param Closure   body
     */
    def exampleTwo = { attrs, body ->
        iterator++

        // define tag name
        def name = (attrs.containsKey('name')) ? attrs.get('name') : UUID.randomUUID()

        // init pagescope
        pageScope.comment = "comment"
        pageScope.handlers = [:]

        // handle body tags
        body()

        // render div element
        out << pageScope.comment
        out << "<div id=\"${name}\">demo: ${iterator}</div>"

        // render initialization javascript
        out << r.script([:], g.render(
                template: '/js/init',
                model: [
                    name        : name,
                    handlers    : pageScope.handlers
                ]
        ))
    }

    def comment = { attrs, body ->
        // add to the page scope
        pageScope.comment = body()
    }

    def whenDone = { attrs, body ->
        // story javascript from the body in the pagescope
        pageScope.handlers.whenDone = body()
    }
}
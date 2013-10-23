package eu.osx.grails

class DemoTagLib {
    static defaultEncodeAs = [all: 'raw']
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
     * render demo tag (uses <script.../>)
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
     * render demo tag (uses r:script)
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

    /**
     * handle comment tag and put the body content in the pageScope
     * @param Map       attributes
     * @param Closure   body
     */
    def comment = { attrs, body ->
        // add to the page scope
        pageScope.comment = body()
    }

    /**
     * handle whenDone javascript event handler tag and put the body content in the pageScope
     * @param Map       attributes
     * @param Closure   body
     */
    def whenDone = { attrs, body ->
        // store javascript from the body in the pagescope
        pageScope.handlers.whenDone = body()
    }
}

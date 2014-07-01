package pl.rafalmag.groovysandbox

import groovy.json.JsonSlurper
import groovyx.net.http.HTTPBuilder

import javax.xml.bind.annotation.XmlSchema

import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.ContentType.XML
import static groovyx.net.http.Method.GET

class RestFull {
    void call() {
//        http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=Calvin%20and%20Hobbes
        def http = new HTTPBuilder( 'http://ajax.googleapis.com/ajax/services/' )

        http.request( GET, JSON ) {
            uri.path = 'search/web'
            uri.query = [ v:'1.0' ,q:"Calvin and Hobbes" ]

//            headers.'User-Agent' = 'Mozilla/5.0 Ubuntu/8.10 Firefox/3.0.4'

            // response handler for a success response code:
            response.success = { resp, json ->
                // http://groovy.codehaus.org/Reading+XML+using+Groovy%27s+XmlSlurper
                // http://groovy.codehaus.org/GPath
                println resp.statusLine
                println json
                println json.getClass()
                println ( json.responseData.results.url)
                assert json.responseData.results.size() == 4
                println (json.responseData.results.findAll{ it.url.contains("wikipedia")}.content )
            }

            // handler for any failure status code:
            response.failure = { resp ->
                println "Unexpected error: ${resp.statusLine.statusCode} : ${resp.statusLine.reasonPhrase}"
            }
        }
    }
}

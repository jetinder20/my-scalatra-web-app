package com.example.app

import org.scalatra._
import scalate.ScalateSupport
import scala.xml.Node

class MyScalatraServlet extends MyScalatraWebAppStack {

  private def displayPage(title: String, content: Seq[Node]) = Template.page(title, content, url(_))

  get("/") {
    <html>
      <body>
        <h1>SCALATRA SEARCH DEMO :</h1>
        <input type="text" name="search"></input>
        <input type="submit" value="Submit"></input>
      </body>
    </html>
  }

  get("/form") {
    displayPage("Scalatra: Form Post Example",
      <form action={url("/post")} method='POST'>
        Post something:
        <input name="submission" type='text'/>
        <input type='submit'/>
      </form>
        <pre>Route: /form</pre>
    )
  }

  post("/post") {
    displayPage("Scalatra: Form Post Result",
      <p>You posted: {params("submission")}</p>
        <pre>Route: /post</pre>
    )
  }

}

object Template {

  def page(title: String, content: Seq[Node], url: String => String = identity _, head: Seq[Node] = Nil, scripts: Seq[String] = Seq.empty, defaultScripts: Seq[String] = Seq("/assets/js/jquery.min.js", "/assets/js/bootstrap.min.js")) = {
    <html lang="en">
      <body>
        <h1>{title}</h1>
        <div class="row">
          <a href={url("/form")}>Form example</a>
          {content}
        </div>
      </body>
    </html>
  }
}

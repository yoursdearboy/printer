# Papers Office

A set of tools and services to automate paper office 📝 routines and integrate to your services.

The name is due to analogy with an office responsible. Imagine you come there and meet:

- 🧑‍💼 Great Reporter - a skilled employee gathering and processing data you ask him on demand, she helps you to boy and solve the problem.
- 👨‍💻 Reporter - he is a junior responsible for managing documents, filling the gaps into templates you provide, printing the documents.
- 🖨 Printer - a device you use «to print» documents.

## Use cases

![Use cases diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.github.com/yoursdearboy/printer/master/src/docs/use-cases.puml)

## Great Reporter

*In development, use Printer.*

Extends Reporter with UI, data sources, full featured scripting support. See Reporter.

## Reporter

*In development, use Printer.*

Standalone report generator. An open source and free alternative to Jasper Reports and Aspose. 

Design inspired by Jasper Reports, MVC pattern and REST architecture:

- Report `Template` is a report definition
- Report `Template` has `data sources`
- Report `Template` has `parameters`
- Report `Template` defines it’s `fields` in a `logic` block
- The rest of report `Template` is a pure `template` using those fields
- To get `Report` we evaluate `Template` applying parameters and fetching data from data sources, evaluating the logic
- `Report` may have different representations - PDF, DOCX, CSV, XLSX
- `Representations` are generated using `Printer`
- `Representations` served through HTTP REST API

*Template is a data class parsed from ____*

*Data source is*

*Parameters is a simple predefined hash.*

*Fields.*

*Logic - JSR code.*

*Template - an XML document, see Printer.*

*Representations are defined by Printer.*

### HTTP API

`GET /reports`  
Lists reports.

`GET /reports/<id>`  
Get single report by id.
Returns only those representations generated before. Because the purpose is to return exactly the generated document. Anyway we can’t be sure to make another representation due potential external changes.

`POST /reports/`  
Creates report. Template is defined through `template` parameter which is path to a template or template file / zip-file or zip (use multipart form data then)

## Printer

Works like a web-browser - it receives «instructions» in an XML document and renders them to the document in requested format. It supports scripting through JSR. No templating or processing capabilities, use Reporter.

`POST /`  
Renders instructions passed in request `body` and returns the document in format defined by `Accept` header.

Instructions are an XML document which looks like HTML, but with a bit different syntax. See format spec below.

```
<document>
  <head>
    <title>Document title</title>
  </head>
  <body>
    <toc/>
    <h1>Header 1</h1>
    <p>Paragraph</p>
    <table>
    <thead>
    <tbody>
     ...
    </tbody>
    </table>
    <script type="groovy">
        document.on('ready', ev => {
            println "Document rendered successfully"
         }
    </script>
  </body>
</document>
```

Accept headers:

- `pdf` - synonym to `docx-pdf`
- `docx`
- `docx-pdf`
- `csv` - not implemented
- `xlsx`- not implemented
- `xlsx-pdf` - not implemented

Document formats `docx` and `docx-pdf` are implemented using docx4j library [1,3]. 

Instructions format uses `docx4j-ImportXHTML` [2] format though may (and probably) will be changed in future.

# Resources

[1] [docx4j library](https://www.docx4java.org/trac/docx4j)
[2] [docx4j-ImportXHTML](https://github.com/plutext/docx4j-ImportXHTML)
[3] [docx4j cheatsheet](https://docx4java.org/docx4j/plutext-docx4j_on_a_page-v800.pdf)
# Introduction to tasks_api

This is a REST API that will provide CRUD operations for the task entity.

The REST API itself will be a [Plack](http://plackperl.org/)-like application, designed to just
be provided a url, http headers and an http body, and will respond based on that neutral information.

This means this app needs a web server that will provide it with the http information it needs.

# MojoHaus Versions Maven Plugin

> :warning: This repository is a fork of the Versions Maven Plugin that is maintained by [Corgibytes](https://corgibytes.com). It includes a [pull request](https://github.com/mojohaus/versions-maven-plugin/pull/636) that hasn't yet been merged into the upstream project. If that pull request gets merged, then this fork will likely be removed.

This is the [versions-maven-plugin](http://www.mojohaus.org/versions-maven-plugin/).
 
[![Apache License, Version 2.0, January 2004](https://img.shields.io/github/license/corgibytes/versions-maven-plugin.svg?label=License)](http://www.apache.org/licenses/)
[![Maven Central](https://img.shields.io/maven-central/v/com.corgibytes/versions-maven-plugin.svg?label=Maven%20Central)](https://search.maven.org/artifact/com.corgibytes/versions-maven-plugin)
[![Build Status](https://github.com/corgibytes/versions-maven-plugin/workflows/GitHub%20CI/badge.svg?branch=master)](https://github.com/corgibytes/versions-maven-plugin/actions/workflows/maven.yml?query=branch%3Amaster)


## Contributing

### Creating Issues

If you find a problem please create an 
[issue in the ticket](https://github.com/mojohaus/versions-maven-plugin/issues)
and describe what is going wrong or what you expect to happen.
If you have a full working example or a log file this is also helpful.
You should of course describe only a single issue in a single ticket and not 
mixing up several different things into a single issue.

### Creating a Pull Request

Before you create a pull request it is necessary to create an issue in
the [ticket system](https://github.com/mojohaus/versions-maven-plugin/issues)
and describe what the problem is or what kind of feature you would like
to add. Afterwards you can create an appropriate pull request.

It is required if you want to get a Pull request to be integrated into please
squash your commits into a single commit which references the issue in the
commit message which looks like this:

```
Fixed #Issue
 o Description.
```

This makes it simpler to merge it and this will also close the
appropriate issue automatically in one go. This make the life as 
maintainer a little bit easier.

A pull request has to fulfill only a single ticket and should never
create/add/fix several issues in one, cause otherwise the history is hard to
read and to understand and makes the maintenance of the issues and pull request
hard or to be honest impossible.


## Releasing

* Make sure `gpg-agent` is running.
* Execute `./mvnw -B release:prepare release:perform`

For publishing the site do the following:

```
cd target/checkout
../mvnw verify site site:stage scm-publish:publish-scm
```

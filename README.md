# TG-API
[![Release Version][release-image]][release-url]
[![Build Status][travis-image]][travis-url]
[![License][license-image]][license-url]
> Unofficial TG-Timetable API for Java.


A Java library to get the current timetable from your DSBMobile account.

## Code example
A very simple example to get the timetable.

```java
TG tg = new TG("username", "password");
TimeTable timeTable = tg.getTimeTable();

Date lastChange = timeTable.getDate();
for (Table table : timeTable.getTables()) {
	Date targetDate = table.getDate();
	for (TableEntry tableEntry : table.getTableEntries()) {
		String schoolClass = tableEntry.getSchoolClass();
		String time = tableEntry.getTime();
		String type = tableEntry.getType();

		String subject = tableEntry.getSubject();
		String replacementSubject = tableEntry.getReplacementSubject();

		String room = tableEntry.getRoom();
		String replacementRoom = tableEntry.getReplacementRoom();

		String text = tableEntry.getText();
	}
}
```

Some options:
 - If you like to summarize the timetable,  you can appand ``.summarize()`` behind ``tg.getTimeTable()``. Would look like this:
``TimeTable timeTable = tg.getTimeTable().summarize();``
 - Another option is the ``.split()`` methode, witch also appand behind ``tg.getTimeTable()``. It splits the TableEntry, if there are more then one classes in the ``schoolClass`` field.

## Release History
* 1.1
    * Java 7 compatibility
	* ``toString()`` update
* 1.0
    * Initial version

## Dependencies
- [Google Gson](https://github.com/google/gson) ([Apache 2 license](https://github.com/google/gson/blob/master/LICENSE)).
- [Jsoup](https://jsoup.org/) ([MIT License](https://jsoup.org/license)).

## Info
© Sematre 2019

Distributed under the **MIT License**. See ``LICENSE`` for more information.

[release-image]: https://img.shields.io/github/release/Sematre/TG-API.svg?style=flat-square
[release-url]: https://github.com/Sematre/TG-API/releases

[travis-image]: https://img.shields.io/travis/com/Sematre/TG-API.svg?style=flat-square
[travis-url]: https://travis-ci.com/Sematre/TG-API

[license-image]: https://img.shields.io/github/license/Sematre/TG-API.svg?style=flat-square
[license-url]: https://github.com/Sematre/TG-API/blob/master/LICENSE
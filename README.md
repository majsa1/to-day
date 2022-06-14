# APEP-2122-group-1

To-day. Everything you need to do today.

This To-day android application is an application where users can keep a list of everything they need to do. It is a
so-called 'todo-list' application.

## Installation

To view and use this application you can log in with username " test " and password " testpw ".

## Basic operation

When the app is opened, you will arrive at the login screen. Here you can log in with your existing login details or choose to register. When registering you can create your own unique code. This is so only you can see or edit your own tasks!

After entering the login details, you will arrive at your own personal task list. Here you can see at a glance which tasks you have. You can check your tasks to "done" or if you accidentally make a mistake, press "done" to view your checked tasks. Here you can "uncheck" your already checked tasks. The task then goes back to the "to do" tasks.

On this screen you can also tap the filter logo to sort your tasks. This can be descending or ascending, according to the deadline.
You can also easily create labels on this screen by simply pressing the label icon. This will take you to the label screen where you can create your own personal labels.You can use these labels when creating your task.

A task consists of a title (name of the task), a deadline, possibly a chosen label, and a description. To view the task in full, press the task from your to do or done task list. This will take you to the view screen. You can only view the task here and not edit it. If you still want to edit the task, press the edit buttonn. Here you come to the edit screen. You can now edit, rename or delete tasks. You can also return directly to your task list from this screen.

If you want to create a task, click on the + at the bottom of your task list. Here you come to the add screen. Here you can create a task by filling in the empty fields and saving. The name of your task, the possible deadline, your own, existing or no label. You can also give an extra description of your task. Handy if, for example, you need to bring or pick up something to be able to carry out your task.

The job is saved in the database. When saving the task in the database, the username is included to protect the user data.
When the task is retrieved from the database, a search is first performed on username and then on task id.


We, APEP-2122-group-1, have chosen to use localDateTime for the deadline. The tasks are retrieved from the database per method. We give the tasks with intents the task Id number and then retrieve the task from the database in the next view.

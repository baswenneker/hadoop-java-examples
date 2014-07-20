# hadoop-java-examples

This repository contains MapReduce examples written in Java.

## Installation

#### No Hadoop installed
If you have no Hadoop instance, use [this](http://baswenneker.github.io/blog/2014/07/09/setting-up-hadoop-using-vagrant.html "Setting up Hadoop using Vagrant") post to create a virtual environment. After you've installed the latest versions of [Vagrant](http://www.vagrantup.com) and [VirtualBox](https://www.virtualbox.org), execute the following commands to set up the environment and access the Hadoop node using SSH:

```bash
$ git clone https://github.com/baswenneker/hadoop-single-node-vagrant.git
$ cd hadoop-single-node-vagrant/
$ git clone https://github.com/baswenneker/hadoop-java-examples.git
$ vagrant up
$ vagrant ssh -- -l hduser # Note the password is hduser
```

#### Hadoop installed
So if you're a boss and already have Hadoop running, just execute the following line on the Hadoop server:

```bash
$ git clone https://github.com/baswenneker/hadoop-java-examples.git
```

## SameTaste example
In this example I show how to calculate what to cook based on the preferences of invitees. The input and output of the program look like:

```
Input:
    John likes Chips 
    John likes Chicken
    John likes Pizza
    Ben likes Chicken
    Ben likes Fish
    Anna likes Vegetables
    Anna likes Cheese
    Anna likes Pizza
    Anna likes Fish

Output:
    Cheese  Anna 
    Chicken Ben John 
    Chips   John 
    Fish    Anna Ben 
    Pizza   Anna Ben John 
    Vegetables  Anna 
```

I've added a sametaste.sh script for convenience:

```
Usage: ./sametaste.sh [--COMMAND] ... [--COMMAND]
    Where COMMAND is one of:

    build [-b]      Compile and create jar file in EXAMPLEDIR/SameTaste.
    cleanup [-c]    Remove Hadoop run output on HDFS (/tmp/testing/sametaste_out).
    input [-i]      Create HDFS input directory (/tmp/testing/sametaste_in) and move tastes.txt to this directory
    run [-r]        Start the Hadoop job
    show [-s]       Show results
    help [-h]       Show this help

Note that the commands are executed in the parameter order.  
```

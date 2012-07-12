#!/bin/bash
lang=$1

if [ 'x'$lang == 'x' ] ; then
	echo usages: $0 locale [venue]
else
	file="config/locales/${2}/${lang}.yml"
	echo "${lang}:" >> $file; find app/views/ -type f | xargs grep _\ \'  | sed -e "s/.*\s_\ ['\"]//" -e "s/['\"].*//" -e "s/\(.*\)/  \1: \1/" | sort | uniq >> $file
	echo $file
fi

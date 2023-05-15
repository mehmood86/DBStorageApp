#!/bin/bash

# check if input file is provided as command line argument
if [ $# -eq 0 ]
  then
    echo "Please provide the input file as command line argument"
    exit 1
fi

# set input and output files
input_file="$1"
output_file="dataset.csv"

# write header to CSV file
#echo "access_cnt,accessed_at,bytes,campaign,closed_at,created_at,deleted_at,eol_at,expired_at,length,name,run_number,updated_at" > $output_file

# loop through each line in the input file
while read line
do
  # extract key value pairs
  access_cnt=$(echo $line | sed 's/.*access_cnt: \([^;]*\);.*/\1/')
  accessed_at=$(echo $line | sed 's/.*accessed_at: \([^;]*\);.*/\1/')
  bytes=$(echo $line | sed 's/.*bytes: \([^;]*\);.*/\1/')
  campaign=$(echo $line | sed 's/.*campaign: \([^;]*\);.*/\1/')
  closed_at=$(echo $line | sed 's/.*closed_at: \([^;]*\);.*/\1/')
  created_at=$(echo $line | sed 's/.*created_at: \([^;]*\);.*/\1/')
  deleted_at=$(echo $line | sed 's/.*deleted_at: \([^;]*\);.*/\1/')
  eol_at=$(echo $line | sed 's/.*eol_at: \([^;]*\);.*/\1/')
  expired_at=$(echo $line | sed 's/.*expired_at: \([^;]*\);.*/\1/')
  length=$(echo $line | sed 's/.*length: \([^;]*\);.*/\1/')
  name=$(echo $line | sed 's/.*name: \([^;]*\);.*/\1/')
  run_number=$(echo $line | sed 's/.*run_number: \([^;]*\);.*/\1/')
  updated_at=$(echo $line | sed 's/.*updated_at: \([^;]*\)/\1/')

  # write values to CSV file
  echo "$access_cnt,$accessed_at,$bytes,$campaign,$closed_at,$created_at,$deleted_at,$eol_at,$expired_at,$length,$name,$run_number,$updated_at" >> $output_file
done < $input_file

Assumptions
-----------
1. Data is already inserted into the database.
2. Added additional functions in FactDbApi interface (createConnection(), closeConnection() and executeQuery()). These 
   functions would be more appropriate to be in the interface since it talks to the database. 
3. Modified gotoAfterLatest() and gotoBeforeEarliest() to return FactDbRecord for convenience.


Result
------
For the database shown in the screenshot the input/output is:
1,name,age = Alice3 3 
2,name,age = Bob2 1 
 

Some ideas which couldn't be implemented because of limited time constraints
----------------------------------------------------------------------------
1. Based on the field types different tables can be maintained so we do not have to go through 
   unnessary rows. We can directly go to the table which has name field and can search for the requried fields.
2. The search in the database could be made faster by choosing subset of rows using binary search strategy i.e., 
   we can take average of FactDbRecord.getMillis() of first and last rows and see which end the currentTimeInMillis
   falls and we can keep repeating the same procedure until we get to the smallest number of rows where the record
   exists.


Test cases
----------
1. Check whether the database connection is established successfully.
2. Run the query and check if the table has some valid records. 
3. Check if the entity id is a position value.
4. Check if the name and age are only string values. 
5. Check if multiple reads on the database retrieves the correct results. 
6. Check whether the database connection is closed successfully. 

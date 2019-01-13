# RestaurantFinder with Location using Doordash Api

MVP architecture

Few keynotes:
- Currently grabbing 1000 entries on 1st launch then storing it in local database
- 1 minute refresh interval is set (can be changed from presenter or dynamically in future), for 1 minute we store data locally and use that
- we can optimize this according to usage and if we have information about open and close hours we can do it every 24 hours(unless location is changed dramatically)  


Next features set:
sorting using different method (we can use sqlite queries for that)

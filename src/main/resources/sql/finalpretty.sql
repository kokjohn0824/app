CREATE TABLE [users] (
  [id] int PRIMARY KEY IDENTITY(1, 1),
  [account] varchar(50),
  [password] varchar(50),
  [register_date] date,
  [change_password_date] date,
  [row] int,
  [fk_member_id] int
)
GO

CREATE TABLE [member] (
  [id] int PRIMARY KEY IDENTITY(1, 1),
  [age] int,
  [height] float,
  [weight] float,
  [bodyFat] float,
  [visceralFat] float,
  [muscleMass] float,
  [changePhoto] varbinary(max),
  [becomeVIP] int
)
GO

CREATE TABLE [daily_record] (
  [id] int PRIMARY KEY IDENTITY(1, 1),
  [weight] int,
  [bodyFat] int,
  [drinkingWater] int,
  [fk_sport_id] int,
  [sport_time] int,
  [fk_food_id] int,
  [food_side] int,
  [fk_member_id] int,
  [date_time] date
)
GO

CREATE TABLE [article] (
  [id] int PRIMARY KEY IDENTITY(1, 1),
  [title] nvarchar(50),
  [text] nvarchar(max),
  [picture] varbinary(max),
  [create_date] date
)
GO

CREATE TABLE [video] (
  [id] int PRIMARY KEY IDENTITY(1, 1),
  [title] nvarchar(50),
  [url] varchar(50),
  [type] nvarchar(50),
  [body_parts] nvarchar(50),
  [picture] varbinary(max)
)
GO

CREATE TABLE [article_like] (
  [id] int PRIMARY KEY IDENTITY(1, 1),
  [fk_article_id] int,
  [fk_member_id] int
)
GO

CREATE TABLE [video_like] (
  [id] int PRIMARY KEY IDENTITY(1, 1),
  [fk_video_id] int,
  [fk_member_id] int
)
GO

CREATE TABLE [product] (
  [id] int PRIMARY KEY IDENTITY(1, 1),
  [title] nvarchar(50),
  [type] nvarchar(50),
  [text] nvarchar(max),
  [picture] varbinary(max),
  [price] int,
  [onsale] int
)
GO

CREATE TABLE [order_detail] (
  [id] int PRIMARY KEY IDENTITY(1, 1),
  [count] int,
  [fk_product_id] int,
  [fk_order_id] int
)
GO

CREATE TABLE [order] (
  [id] int PRIMARY KEY IDENTITY(1, 1),
  [ship] nvarchar(max),
  [status] nvarchar(max),
  [address] nvarchar(50),
  [fk_member_id] int
)
GO

CREATE TABLE [post] (
  [id] int PRIMARY KEY IDENTITY(1, 1),
  [title] nvarchar(50),
  [text] nvarchar(max),
  [create_date] date,
  [update_date] date,
  [fk_member_id] int
)
GO

CREATE TABLE [response] (
  [id] int PRIMARY KEY IDENTITY(1, 1),
  [text] nvarchar(max),
  [create_date] date,
  [fk_post] int,
  [fk_member_id] int
)
GO

CREATE TABLE [food] (
  [id] int PRIMARY KEY IDENTITY(1, 1),
  [foodname] nvarchar(50),
  [calorie] int
)
GO

CREATE TABLE [sports] (
  [id] int PRIMARY KEY IDENTITY(1, 1),
  [sportsname] nvarchar(50),
  [calorie] int
)
GO

ALTER TABLE [users] ADD FOREIGN KEY ([fk_member_id]) REFERENCES [member] ([id])
GO

ALTER TABLE [article_like] ADD FOREIGN KEY ([fk_article_id]) REFERENCES [article] ([id])
GO

ALTER TABLE [article_like] ADD FOREIGN KEY ([fk_member_id]) REFERENCES [member] ([id])
GO

ALTER TABLE [video_like] ADD FOREIGN KEY ([fk_video_id]) REFERENCES [video] ([id])
GO

ALTER TABLE [video_like] ADD FOREIGN KEY ([fk_member_id]) REFERENCES [member] ([id])
GO

ALTER TABLE [order] ADD FOREIGN KEY ([fk_member_id]) REFERENCES [member] ([id])
GO

ALTER TABLE [order_detail] ADD FOREIGN KEY ([fk_product_id]) REFERENCES [product] ([id])
GO

ALTER TABLE [order_detail] ADD FOREIGN KEY ([fk_order_id]) REFERENCES [order] ([id])
GO

ALTER TABLE [post] ADD FOREIGN KEY ([fk_member_id]) REFERENCES [member] ([id])
GO

ALTER TABLE [response] ADD FOREIGN KEY ([fk_member_id]) REFERENCES [member] ([id])
GO

ALTER TABLE [response] ADD FOREIGN KEY ([fk_post]) REFERENCES [post] ([id])
GO

ALTER TABLE [daily_record] ADD FOREIGN KEY ([fk_member_id]) REFERENCES [member] ([id])
GO

ALTER TABLE [daily_record] ADD FOREIGN KEY ([fk_food_id]) REFERENCES [food] ([id])
GO

ALTER TABLE [daily_record] ADD FOREIGN KEY ([fk_sport_id]) REFERENCES [sports] ([id])
GO

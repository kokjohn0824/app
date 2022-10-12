USE [finalprettydb]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[daily_record](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[weight] [int] NULL,
	[bodyFat] [int] NULL,
	[drinkingWater] [int] NULL,
	[fk_sport_id] [int] NULL,
	[sport_time] [int] NULL,
	[fk_food_id] [int] NULL,
	[food_side] [int] NULL,
	[fk_member_id] [int] NULL,
	[date_time] [date] NULL
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[daily_record] ADD PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[daily_record]  WITH CHECK ADD FOREIGN KEY([fk_food_id])
REFERENCES [dbo].[food] ([id])
GO
ALTER TABLE [dbo].[daily_record]  WITH CHECK ADD FOREIGN KEY([fk_member_id])
REFERENCES [dbo].[member] ([id])
GO
ALTER TABLE [dbo].[daily_record]  WITH CHECK ADD FOREIGN KEY([fk_sport_id])
REFERENCES [dbo].[sports] ([id])
GO

USE [finalprettydb]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[video_like](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[fk_video_id] [int] NULL,
	[fk_member_id] [int] NULL
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[video_like] ADD PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[video_like]  WITH CHECK ADD FOREIGN KEY([fk_member_id])
REFERENCES [dbo].[member] ([id])
GO
ALTER TABLE [dbo].[video_like]  WITH CHECK ADD FOREIGN KEY([fk_video_id])
REFERENCES [dbo].[video] ([id])
GO

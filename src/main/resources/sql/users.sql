USE [finalprettydb]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[users](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[account] [varchar](50) NULL,
	[password] [varchar](50) NULL,
	[register_date] [date] NULL,
	[change_password_date] [date] NULL,
	[row] [int] NULL,
	[fk_member_id] [int] NULL
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[users] ADD PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[users]  WITH CHECK ADD FOREIGN KEY([fk_member_id])
REFERENCES [dbo].[member] ([id])
GO

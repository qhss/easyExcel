<?xml version="1.0"?>
<?mso-application progid="Excel.Sheet"?>
<Workbook xmlns="urn:schemas-microsoft-com:office:spreadsheet"
          xmlns:o="urn:schemas-microsoft-com:office:office"
          xmlns:x="urn:schemas-microsoft-com:office:excel"
          xmlns:dt="uuid:C2F41010-65B3-11d1-A29F-00AA00C14882"
          xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet"
          xmlns:html="http://www.w3.org/TR/REC-html40">
    <DocumentProperties xmlns="urn:schemas-microsoft-com:office:office">
        <Author>Admin</Author>
        <LastAuthor>Admin</LastAuthor>
        <Created>2020-12-16T01:48:32Z</Created>
        <LastSaved>2020-12-16T01:53:21Z</LastSaved>
        <Version>15.00</Version>
    </DocumentProperties>
    <CustomDocumentProperties xmlns="urn:schemas-microsoft-com:office:office">
        <KSOProductBuildVer dt:dt="string">2052-11.1.0.10228</KSOProductBuildVer>
    </CustomDocumentProperties>
    <OfficeDocumentSettings xmlns="urn:schemas-microsoft-com:office:office">
        <AllowPNG/>
    </OfficeDocumentSettings>
    <ExcelWorkbook xmlns="urn:schemas-microsoft-com:office:excel">
        <WindowHeight>12540</WindowHeight>
        <WindowWidth>24225</WindowWidth>
        <WindowTopX>0</WindowTopX>
        <WindowTopY>0</WindowTopY>
        <ProtectStructure>False</ProtectStructure>
        <ProtectWindows>False</ProtectWindows>
    </ExcelWorkbook>
    <Styles>
        <Style ss:ID="Default" ss:Name="Normal">
            <Alignment ss:Vertical="Center"/>
            <Borders/>
            <Font ss:FontName="宋体" x:CharSet="134" ss:Size="11" ss:Color="#000000"/>
            <Interior/>
            <NumberFormat/>
            <Protection/>
        </Style>
        <Style ss:ID="s65">
            <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
            <Interior ss:Color="#5B9BD5" ss:Pattern="Solid"/>
        </Style>
    </Styles>
    <Worksheet ss:Name="Sheet1">
        <Table ss:ExpandedColumnCount="3" ss:ExpandedRowCount="2" x:FullColumns="1"
               x:FullRows="1" ss:DefaultColumnWidth="54" ss:DefaultRowHeight="13.5">
            <Row>
                <Cell ss:Index="2" ss:MergeAcross="1" ss:StyleID="s65"><Data ss:Type="String">学生信息表</Data></Cell>
            </Row>
            <Row>
                <Cell ss:Index="2"><Data ss:Type="String">姓名</Data></Cell>
                <Cell><Data ss:Type="String">性别</Data></Cell>
            </Row>
            <#list info as i>
                <Row>
                    <Cell ss:Index="2"><Data ss:Type="String">${i.name!}</Data></Cell>
                    <Cell><Data ss:Type="String">${i.gender!}</Data></Cell>
                </Row>
            </#list>
        </Table>
        <WorksheetOptions xmlns="urn:schemas-microsoft-com:office:excel">
            <Selected/>
            <Panes>
                <Pane>
                    <Number>3</Number>
                    <ActiveRow>7</ActiveRow>
                    <ActiveCol>3</ActiveCol>
                </Pane>
            </Panes>
            <ProtectObjects>False</ProtectObjects>
            <ProtectScenarios>False</ProtectScenarios>
        </WorksheetOptions>
    </Worksheet>
</Workbook>

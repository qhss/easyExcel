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
        <Created>2020-12-16T07:16:02Z</Created>
        <LastSaved>2020-12-16T07:33:22Z</LastSaved>
        <Version>15.00</Version>
    </DocumentProperties>
    <CustomDocumentProperties xmlns="urn:schemas-microsoft-com:office:office">
        <KSOProductBuildVer dt:dt="string">2052-11.1.0.10228</KSOProductBuildVer>
    </CustomDocumentProperties>
    <OfficeDocumentSettings xmlns="urn:schemas-microsoft-com:office:office">
        <AllowPNG/>
    </OfficeDocumentSettings>
    <ExcelWorkbook xmlns="urn:schemas-microsoft-com:office:excel">
        <WindowHeight>12435</WindowHeight>
        <WindowWidth>28800</WindowWidth>
        <WindowTopX>0</WindowTopX>
        <WindowTopY>0</WindowTopY>
        <ProtectStructure>False</ProtectStructure>
        <ProtectWindows>False</ProtectWindows>
    </ExcelWorkbook>
    <Styles>
        <Style ss:ID="Default" ss:Name="Normal">
            <Alignment ss:Vertical="Center"/> <Borders/> <Font ss:FontName="宋体" x:CharSet="134" ss:Size="11" ss:Color="#000000"/> <Interior/> <NumberFormat/> <Protection/>
        </Style>
        <Style ss:ID="s90">
            <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
        </Style>
        <Style ss:ID="s65">
            <Alignment ss:Horizontal="Center" ss:Vertical="Center"/> <Interior ss:Color="#5B9BD5" ss:Pattern="Solid"/>
        </Style>
        <Style ss:ID="s91">
            <Alignment ss:Vertical="Center"/>
        </Style>
    </Styles>
    <Worksheet ss:Name="Sheet1">
        <Table ss:ExpandedColumnCount="13" ss:ExpandedRowCount="4" x:FullColumns="1"
               x:FullRows="1" ss:DefaultColumnWidth="54" ss:DefaultRowHeight="13.5">
            <Column ss:Index="2" ss:AutoFitWidth="0" ss:Width="111.75"/>
            <Row ss:AutoFitHeight="0">
                <Cell ss:MergeAcross="12" ss:MergeDown="1" ss:StyleID="s90"><Data
                            ss:Type="String">${month!}月周次表</Data></Cell>
            </Row>
            <Row ss:AutoFitHeight="0"/>
            <Row ss:AutoFitHeight="0">
                <Cell ss:StyleID="s91"><Data ss:Type="String">日期</Data></Cell>
                <#list daylist as d>
                    <#if d==2||d==3>
                        <Cell ss:StyleID="s65"><Data ss:Type="String">${d!}</Data></Cell>
                    <#else>
<#--                    <Cell><Data ss:Type="String">${d.day!}</Data></Cell>-->
                    <Cell ><Data ss:Type="String">${d!}</Data></Cell>
                    </#if>
                </#list>
            </Row>
            <Row ss:AutoFitHeight="0">
                <Cell ss:StyleID="s91"><Data ss:Type="String">周次 </Data></Cell>
                <#list weeklist as w>
<#--                    <Cell><Data ss:Type="String">${w.week!}</Data></Cell>-->
                    <Cell><Data ss:Type="String">${w!}</Data></Cell>
                </#list>

            </Row>
        </Table>
        <WorksheetOptions xmlns="urn:schemas-microsoft-com:office:excel">
            <Unsynced/>
            <PageBreakZoom>100</PageBreakZoom>
            <Selected/>
            <Panes>
                <Pane>
                    <Number>3</Number>
                    <ActiveRow>27</ActiveRow>
                    <ActiveCol>8</ActiveCol>
                </Pane>
            </Panes>
            <ProtectObjects>False</ProtectObjects>
            <ProtectScenarios>False</ProtectScenarios>
        </WorksheetOptions>
    </Worksheet>
</Workbook>

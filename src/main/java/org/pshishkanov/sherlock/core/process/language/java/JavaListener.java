package org.pshishkanov.sherlock.core.process.language.java;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public class JavaListener implements IJavaListener {

    private List<String> tokens = new ArrayList<>();

    @Override
    public List<String> getTokens() {
        return tokens;
    }

    @Override public void enterLiteral(JavaParser.LiteralContext ctx) { }

    @Override public void exitLiteral(JavaParser.LiteralContext ctx) { }

    @Override public void enterType(JavaParser.TypeContext ctx) { }

    @Override public void exitType(JavaParser.TypeContext ctx) { }

    @Override public void enterPrimitiveType(JavaParser.PrimitiveTypeContext ctx) { }

    @Override public void exitPrimitiveType(JavaParser.PrimitiveTypeContext ctx) { }

    @Override public void enterNumericType(JavaParser.NumericTypeContext ctx) { }

    @Override public void exitNumericType(JavaParser.NumericTypeContext ctx) { }

    @Override public void enterIntegralType(JavaParser.IntegralTypeContext ctx) { }

    @Override public void exitIntegralType(JavaParser.IntegralTypeContext ctx) { }

    @Override public void enterFloatingPointType(JavaParser.FloatingPointTypeContext ctx) { }

    @Override public void exitFloatingPointType(JavaParser.FloatingPointTypeContext ctx) { }

    @Override public void enterReferenceType(JavaParser.ReferenceTypeContext ctx) { }

    @Override public void exitReferenceType(JavaParser.ReferenceTypeContext ctx) { }

    @Override public void enterClassOrInterfaceType(JavaParser.ClassOrInterfaceTypeContext ctx) { }

    @Override public void exitClassOrInterfaceType(JavaParser.ClassOrInterfaceTypeContext ctx) { }

    @Override public void enterClassType(JavaParser.ClassTypeContext ctx) { }

    @Override public void exitClassType(JavaParser.ClassTypeContext ctx) { }

    @Override public void enterClassType_lf_classOrInterfaceType(JavaParser.ClassType_lf_classOrInterfaceTypeContext ctx) { }

    @Override public void exitClassType_lf_classOrInterfaceType(JavaParser.ClassType_lf_classOrInterfaceTypeContext ctx) { }

    @Override public void enterClassType_lfno_classOrInterfaceType(JavaParser.ClassType_lfno_classOrInterfaceTypeContext ctx) { }

    @Override public void exitClassType_lfno_classOrInterfaceType(JavaParser.ClassType_lfno_classOrInterfaceTypeContext ctx) { }

    @Override public void enterInterfaceType(JavaParser.InterfaceTypeContext ctx) { }

    @Override public void exitInterfaceType(JavaParser.InterfaceTypeContext ctx) { }

    @Override public void enterInterfaceType_lf_classOrInterfaceType(JavaParser.InterfaceType_lf_classOrInterfaceTypeContext ctx) { }

    @Override public void exitInterfaceType_lf_classOrInterfaceType(JavaParser.InterfaceType_lf_classOrInterfaceTypeContext ctx) { }

    @Override public void enterInterfaceType_lfno_classOrInterfaceType(JavaParser.InterfaceType_lfno_classOrInterfaceTypeContext ctx) { }

    @Override public void exitInterfaceType_lfno_classOrInterfaceType(JavaParser.InterfaceType_lfno_classOrInterfaceTypeContext ctx) { }

    @Override public void enterTypeVariable(JavaParser.TypeVariableContext ctx) { }

    @Override public void exitTypeVariable(JavaParser.TypeVariableContext ctx) { }

    @Override public void enterArrayType(JavaParser.ArrayTypeContext ctx) { }

    @Override public void exitArrayType(JavaParser.ArrayTypeContext ctx) { }

    @Override public void enterDims(JavaParser.DimsContext ctx) { }

    @Override public void exitDims(JavaParser.DimsContext ctx) { }

    @Override public void enterTypeParameter(JavaParser.TypeParameterContext ctx) { }

    @Override public void exitTypeParameter(JavaParser.TypeParameterContext ctx) { }

    @Override public void enterTypeParameterModifier(JavaParser.TypeParameterModifierContext ctx) { }

    @Override public void exitTypeParameterModifier(JavaParser.TypeParameterModifierContext ctx) { }

    @Override public void enterTypeBound(JavaParser.TypeBoundContext ctx) { }

    @Override public void exitTypeBound(JavaParser.TypeBoundContext ctx) { }

    @Override public void enterAdditionalBound(JavaParser.AdditionalBoundContext ctx) { }

    @Override public void exitAdditionalBound(JavaParser.AdditionalBoundContext ctx) { }

    @Override public void enterTypeArguments(JavaParser.TypeArgumentsContext ctx) { }

    @Override public void exitTypeArguments(JavaParser.TypeArgumentsContext ctx) { }

    @Override public void enterTypeArgumentList(JavaParser.TypeArgumentListContext ctx) { }

    @Override public void exitTypeArgumentList(JavaParser.TypeArgumentListContext ctx) { }

    @Override public void enterTypeArgument(JavaParser.TypeArgumentContext ctx) {
        tokens.add("GENERIC");
    }

    @Override public void exitTypeArgument(JavaParser.TypeArgumentContext ctx) { }

    @Override public void enterWildcard(JavaParser.WildcardContext ctx) { }

    @Override public void exitWildcard(JavaParser.WildcardContext ctx) { }

    @Override public void enterWildcardBounds(JavaParser.WildcardBoundsContext ctx) { }

    @Override public void exitWildcardBounds(JavaParser.WildcardBoundsContext ctx) { }

    @Override public void enterPackageName(JavaParser.PackageNameContext ctx) { }

    @Override public void exitPackageName(JavaParser.PackageNameContext ctx) { }

    @Override public void enterTypeName(JavaParser.TypeNameContext ctx) { }

    @Override public void exitTypeName(JavaParser.TypeNameContext ctx) { }

    @Override public void enterPackageOrTypeName(JavaParser.PackageOrTypeNameContext ctx) { }

    @Override public void exitPackageOrTypeName(JavaParser.PackageOrTypeNameContext ctx) { }

    @Override public void enterExpressionName(JavaParser.ExpressionNameContext ctx) { }

    @Override public void exitExpressionName(JavaParser.ExpressionNameContext ctx) { }

    @Override public void enterMethodName(JavaParser.MethodNameContext ctx) { }

    @Override public void exitMethodName(JavaParser.MethodNameContext ctx) { }

    @Override public void enterAmbiguousName(JavaParser.AmbiguousNameContext ctx) { }

    @Override public void exitAmbiguousName(JavaParser.AmbiguousNameContext ctx) { }

    @Override public void enterCompilationUnit(JavaParser.CompilationUnitContext ctx) { }

    @Override public void exitCompilationUnit(JavaParser.CompilationUnitContext ctx) { }

    @Override public void enterPackageDeclaration(JavaParser.PackageDeclarationContext ctx) {
        tokens.add("PACKAGE");
    }

    @Override public void exitPackageDeclaration(JavaParser.PackageDeclarationContext ctx) { }

    @Override public void enterPackageModifier(JavaParser.PackageModifierContext ctx) { }

    @Override public void exitPackageModifier(JavaParser.PackageModifierContext ctx) { }

    @Override public void enterImportDeclaration(JavaParser.ImportDeclarationContext ctx) {
        tokens.add("IMPORT");
    }

    @Override public void exitImportDeclaration(JavaParser.ImportDeclarationContext ctx) { }

    @Override public void enterSingleTypeImportDeclaration(JavaParser.SingleTypeImportDeclarationContext ctx) { }

    @Override public void exitSingleTypeImportDeclaration(JavaParser.SingleTypeImportDeclarationContext ctx) { }

    @Override public void enterTypeImportOnDemandDeclaration(JavaParser.TypeImportOnDemandDeclarationContext ctx) { }

    @Override public void exitTypeImportOnDemandDeclaration(JavaParser.TypeImportOnDemandDeclarationContext ctx) { }

    @Override public void enterSingleStaticImportDeclaration(JavaParser.SingleStaticImportDeclarationContext ctx) { }

    @Override public void exitSingleStaticImportDeclaration(JavaParser.SingleStaticImportDeclarationContext ctx) { }

    @Override public void enterStaticImportOnDemandDeclaration(JavaParser.StaticImportOnDemandDeclarationContext ctx) { }

    @Override public void exitStaticImportOnDemandDeclaration(JavaParser.StaticImportOnDemandDeclarationContext ctx) { }

    @Override public void enterTypeDeclaration(JavaParser.TypeDeclarationContext ctx) { }

    @Override public void exitTypeDeclaration(JavaParser.TypeDeclarationContext ctx) { }

    @Override public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) { }

    @Override public void exitClassDeclaration(JavaParser.ClassDeclarationContext ctx) { }

    @Override public void enterNormalClassDeclaration(JavaParser.NormalClassDeclarationContext ctx) {
        tokens.add("CLASS {");
    }

    @Override public void exitNormalClassDeclaration(JavaParser.NormalClassDeclarationContext ctx) {
        tokens.add("} CLASS");
    }

    @Override public void enterClassModifier(JavaParser.ClassModifierContext ctx) { }

    @Override public void exitClassModifier(JavaParser.ClassModifierContext ctx) { }

    @Override public void enterTypeParameters(JavaParser.TypeParametersContext ctx) { }

    @Override public void exitTypeParameters(JavaParser.TypeParametersContext ctx) { }

    @Override public void enterTypeParameterList(JavaParser.TypeParameterListContext ctx) { }

    @Override public void exitTypeParameterList(JavaParser.TypeParameterListContext ctx) { }

    @Override public void enterSuperclass(JavaParser.SuperclassContext ctx) { }

    @Override public void exitSuperclass(JavaParser.SuperclassContext ctx) { }

    @Override public void enterSuperinterfaces(JavaParser.SuperinterfacesContext ctx) { }

    @Override public void exitSuperinterfaces(JavaParser.SuperinterfacesContext ctx) { }

    @Override public void enterInterfaceTypeList(JavaParser.InterfaceTypeListContext ctx) { }

    @Override public void exitInterfaceTypeList(JavaParser.InterfaceTypeListContext ctx) { }

    @Override public void enterClassBody(JavaParser.ClassBodyContext ctx) { }

    @Override public void exitClassBody(JavaParser.ClassBodyContext ctx) { }

    @Override public void enterClassBodyDeclaration(JavaParser.ClassBodyDeclarationContext ctx) { }

    @Override public void exitClassBodyDeclaration(JavaParser.ClassBodyDeclarationContext ctx) { }

    @Override public void enterClassMemberDeclaration(JavaParser.ClassMemberDeclarationContext ctx) { }

    @Override public void exitClassMemberDeclaration(JavaParser.ClassMemberDeclarationContext ctx) { }

    @Override public void enterFieldDeclaration(JavaParser.FieldDeclarationContext ctx) { }

    @Override public void exitFieldDeclaration(JavaParser.FieldDeclarationContext ctx) { }

    @Override public void enterFieldModifier(JavaParser.FieldModifierContext ctx) { }

    @Override public void exitFieldModifier(JavaParser.FieldModifierContext ctx) { }

    @Override public void enterVariableDeclaratorList(JavaParser.VariableDeclaratorListContext ctx) {
        tokens.add("VARDEF");
    }

    @Override public void exitVariableDeclaratorList(JavaParser.VariableDeclaratorListContext ctx) { }

    @Override public void enterVariableDeclarator(JavaParser.VariableDeclaratorContext ctx) { }

    @Override public void exitVariableDeclarator(JavaParser.VariableDeclaratorContext ctx) { }

    @Override public void enterVariableDeclaratorId(JavaParser.VariableDeclaratorIdContext ctx) { }

    @Override public void exitVariableDeclaratorId(JavaParser.VariableDeclaratorIdContext ctx) { }

    @Override public void enterVariableInitializer(JavaParser.VariableInitializerContext ctx) {
        if (!(ctx.parent instanceof JavaParser.ArrayInitializerContext)) {
            tokens.add("ASSIGN");
        }
    }

    @Override public void exitVariableInitializer(JavaParser.VariableInitializerContext ctx) { }

    @Override public void enterUnannType(JavaParser.UnannTypeContext ctx) { }

    @Override public void exitUnannType(JavaParser.UnannTypeContext ctx) { }

    @Override public void enterUnannPrimitiveType(JavaParser.UnannPrimitiveTypeContext ctx) { }

    @Override public void exitUnannPrimitiveType(JavaParser.UnannPrimitiveTypeContext ctx) { }

    @Override public void enterUnannReferenceType(JavaParser.UnannReferenceTypeContext ctx) { }

    @Override public void exitUnannReferenceType(JavaParser.UnannReferenceTypeContext ctx) { }

    @Override public void enterUnannClassOrInterfaceType(JavaParser.UnannClassOrInterfaceTypeContext ctx) { }

    @Override public void exitUnannClassOrInterfaceType(JavaParser.UnannClassOrInterfaceTypeContext ctx) { }

    @Override public void enterUnannClassType(JavaParser.UnannClassTypeContext ctx) { }

    @Override public void exitUnannClassType(JavaParser.UnannClassTypeContext ctx) { }

    @Override public void enterUnannClassType_lf_unannClassOrInterfaceType(JavaParser.UnannClassType_lf_unannClassOrInterfaceTypeContext ctx) { }

    @Override public void exitUnannClassType_lf_unannClassOrInterfaceType(JavaParser.UnannClassType_lf_unannClassOrInterfaceTypeContext ctx) { }

    @Override public void enterUnannClassType_lfno_unannClassOrInterfaceType(JavaParser.UnannClassType_lfno_unannClassOrInterfaceTypeContext ctx) { }

    @Override public void exitUnannClassType_lfno_unannClassOrInterfaceType(JavaParser.UnannClassType_lfno_unannClassOrInterfaceTypeContext ctx) { }

    @Override public void enterUnannInterfaceType(JavaParser.UnannInterfaceTypeContext ctx) { }

    @Override public void exitUnannInterfaceType(JavaParser.UnannInterfaceTypeContext ctx) { }

    @Override public void enterUnannInterfaceType_lf_unannClassOrInterfaceType(JavaParser.UnannInterfaceType_lf_unannClassOrInterfaceTypeContext ctx) { }

    @Override public void exitUnannInterfaceType_lf_unannClassOrInterfaceType(JavaParser.UnannInterfaceType_lf_unannClassOrInterfaceTypeContext ctx) { }

    @Override public void enterUnannInterfaceType_lfno_unannClassOrInterfaceType(JavaParser.UnannInterfaceType_lfno_unannClassOrInterfaceTypeContext ctx) { }

    @Override public void exitUnannInterfaceType_lfno_unannClassOrInterfaceType(JavaParser.UnannInterfaceType_lfno_unannClassOrInterfaceTypeContext ctx) { }

    @Override public void enterUnannTypeVariable(JavaParser.UnannTypeVariableContext ctx) { }

    @Override public void exitUnannTypeVariable(JavaParser.UnannTypeVariableContext ctx) { }

    @Override public void enterUnannArrayType(JavaParser.UnannArrayTypeContext ctx) { }

    @Override public void exitUnannArrayType(JavaParser.UnannArrayTypeContext ctx) { }

    @Override public void enterMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
        if (ctx.start.getText().equals("void")) tokens.add("VOID");
        tokens.add("METHOD {");
    }

    @Override public void exitMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
        tokens.add("} METHOD");
    }

    @Override public void enterMethodModifier(JavaParser.MethodModifierContext ctx) { }

    @Override public void exitMethodModifier(JavaParser.MethodModifierContext ctx) { }

    @Override public void enterMethodHeader(JavaParser.MethodHeaderContext ctx) { }

    @Override public void exitMethodHeader(JavaParser.MethodHeaderContext ctx) { }

    @Override public void enterResult(JavaParser.ResultContext ctx) { }

    @Override public void exitResult(JavaParser.ResultContext ctx) { }

    @Override public void enterMethodDeclarator(JavaParser.MethodDeclaratorContext ctx) { }

    @Override public void exitMethodDeclarator(JavaParser.MethodDeclaratorContext ctx) { }

    @Override public void enterFormalParameterList(JavaParser.FormalParameterListContext ctx) { }

    @Override public void exitFormalParameterList(JavaParser.FormalParameterListContext ctx) { }

    @Override public void enterFormalParameters(JavaParser.FormalParametersContext ctx) { }

    @Override public void exitFormalParameters(JavaParser.FormalParametersContext ctx) { }

    @Override public void enterFormalParameter(JavaParser.FormalParameterContext ctx) { }

    @Override public void exitFormalParameter(JavaParser.FormalParameterContext ctx) { }

    @Override public void enterVariableModifier(JavaParser.VariableModifierContext ctx) { }

    @Override public void exitVariableModifier(JavaParser.VariableModifierContext ctx) { }

    @Override public void enterLastFormalParameter(JavaParser.LastFormalParameterContext ctx) { }

    @Override public void exitLastFormalParameter(JavaParser.LastFormalParameterContext ctx) { }

    @Override public void enterReceiverParameter(JavaParser.ReceiverParameterContext ctx) { }

    @Override public void exitReceiverParameter(JavaParser.ReceiverParameterContext ctx) { }

    @Override public void enterThrows_(JavaParser.Throws_Context ctx) { }

    @Override public void exitThrows_(JavaParser.Throws_Context ctx) { }

    @Override public void enterExceptionTypeList(JavaParser.ExceptionTypeListContext ctx) { }

    @Override public void exitExceptionTypeList(JavaParser.ExceptionTypeListContext ctx) { }

    @Override public void enterExceptionType(JavaParser.ExceptionTypeContext ctx) { }

    @Override public void exitExceptionType(JavaParser.ExceptionTypeContext ctx) { }

    @Override public void enterMethodBody(JavaParser.MethodBodyContext ctx) { }

    @Override public void exitMethodBody(JavaParser.MethodBodyContext ctx) { }

    @Override public void enterInstanceInitializer(JavaParser.InstanceInitializerContext ctx) { }

    @Override public void exitInstanceInitializer(JavaParser.InstanceInitializerContext ctx) { }

    @Override public void enterStaticInitializer(JavaParser.StaticInitializerContext ctx) { }

    @Override public void exitStaticInitializer(JavaParser.StaticInitializerContext ctx) { }

    @Override public void enterConstructorDeclaration(JavaParser.ConstructorDeclarationContext ctx) {
        tokens.add("CONSTR {");
    }

    @Override public void exitConstructorDeclaration(JavaParser.ConstructorDeclarationContext ctx) {
        tokens.add("} CONSTR");
    }

    @Override public void enterConstructorModifier(JavaParser.ConstructorModifierContext ctx) { }

    @Override public void exitConstructorModifier(JavaParser.ConstructorModifierContext ctx) { }

    @Override public void enterConstructorDeclarator(JavaParser.ConstructorDeclaratorContext ctx) { }

    @Override public void exitConstructorDeclarator(JavaParser.ConstructorDeclaratorContext ctx) { }

    @Override public void enterSimpleTypeName(JavaParser.SimpleTypeNameContext ctx) { }

    @Override public void exitSimpleTypeName(JavaParser.SimpleTypeNameContext ctx) { }

    @Override public void enterConstructorBody(JavaParser.ConstructorBodyContext ctx) { }

    @Override public void exitConstructorBody(JavaParser.ConstructorBodyContext ctx) { }

    @Override public void enterExplicitConstructorInvocation(JavaParser.ExplicitConstructorInvocationContext ctx) {
        tokens.add("APPLY");
    }

    @Override public void exitExplicitConstructorInvocation(JavaParser.ExplicitConstructorInvocationContext ctx) { }

    @Override public void enterEnumDeclaration(JavaParser.EnumDeclarationContext ctx) {
        tokens.add("ENUM {");
    }

    @Override public void exitEnumDeclaration(JavaParser.EnumDeclarationContext ctx) {
        tokens.add("} ENUM");
    }

    @Override public void enterEnumBody(JavaParser.EnumBodyContext ctx) { }

    @Override public void exitEnumBody(JavaParser.EnumBodyContext ctx) { }

    @Override public void enterEnumConstantList(JavaParser.EnumConstantListContext ctx) { }

    @Override public void exitEnumConstantList(JavaParser.EnumConstantListContext ctx) { }

    @Override public void enterEnumConstant(JavaParser.EnumConstantContext ctx) {
        tokens.add("ENUM_CLASS");
    }

    @Override public void exitEnumConstant(JavaParser.EnumConstantContext ctx) { }

    @Override public void enterEnumConstantModifier(JavaParser.EnumConstantModifierContext ctx) { }

    @Override public void exitEnumConstantModifier(JavaParser.EnumConstantModifierContext ctx) { }

    @Override public void enterEnumBodyDeclarations(JavaParser.EnumBodyDeclarationsContext ctx) { }

    @Override public void exitEnumBodyDeclarations(JavaParser.EnumBodyDeclarationsContext ctx) { }

    @Override public void enterInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx) { }

    @Override public void exitInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx) { }

    @Override public void enterNormalInterfaceDeclaration(JavaParser.NormalInterfaceDeclarationContext ctx) {
        tokens.add("INTERFACE {");
    }

    @Override public void exitNormalInterfaceDeclaration(JavaParser.NormalInterfaceDeclarationContext ctx) {
        tokens.add("} INTERFACE");
    }

    @Override public void enterInterfaceModifier(JavaParser.InterfaceModifierContext ctx) { }

    @Override public void exitInterfaceModifier(JavaParser.InterfaceModifierContext ctx) { }

    @Override public void enterExtendsInterfaces(JavaParser.ExtendsInterfacesContext ctx) { }

    @Override public void exitExtendsInterfaces(JavaParser.ExtendsInterfacesContext ctx) { }

    @Override public void enterInterfaceBody(JavaParser.InterfaceBodyContext ctx) { }

    @Override public void exitInterfaceBody(JavaParser.InterfaceBodyContext ctx) { }

    @Override public void enterInterfaceMemberDeclaration(JavaParser.InterfaceMemberDeclarationContext ctx) { }

    @Override public void exitInterfaceMemberDeclaration(JavaParser.InterfaceMemberDeclarationContext ctx) { }

    @Override public void enterConstantDeclaration(JavaParser.ConstantDeclarationContext ctx) {
        tokens.add("VARDEF");
    }

    @Override public void exitConstantDeclaration(JavaParser.ConstantDeclarationContext ctx) { }

    @Override public void enterConstantModifier(JavaParser.ConstantModifierContext ctx) { }

    @Override public void exitConstantModifier(JavaParser.ConstantModifierContext ctx) { }

    @Override public void enterInterfaceMethodDeclaration(JavaParser.InterfaceMethodDeclarationContext ctx) { }

    @Override public void exitInterfaceMethodDeclaration(JavaParser.InterfaceMethodDeclarationContext ctx) { }

    @Override public void enterInterfaceMethodModifier(JavaParser.InterfaceMethodModifierContext ctx) { }

    @Override public void exitInterfaceMethodModifier(JavaParser.InterfaceMethodModifierContext ctx) { }

    @Override public void enterAnnotationTypeDeclaration(JavaParser.AnnotationTypeDeclarationContext ctx) {
        tokens.add("ANNOTATION_TYPE_DECLARATION_BEGIN {");
    }

    @Override public void exitAnnotationTypeDeclaration(JavaParser.AnnotationTypeDeclarationContext ctx) {
        tokens.add("} ANNOTATION_TYPE_DECLARATION_END");
    }

    @Override public void enterAnnotationTypeBody(JavaParser.AnnotationTypeBodyContext ctx) { }

    @Override public void exitAnnotationTypeBody(JavaParser.AnnotationTypeBodyContext ctx) { }

    @Override public void enterAnnotationTypeMemberDeclaration(JavaParser.AnnotationTypeMemberDeclarationContext ctx) { }

    @Override public void exitAnnotationTypeMemberDeclaration(JavaParser.AnnotationTypeMemberDeclarationContext ctx) { }

    @Override public void enterAnnotationTypeElementDeclaration(JavaParser.AnnotationTypeElementDeclarationContext ctx) { }

    @Override public void exitAnnotationTypeElementDeclaration(JavaParser.AnnotationTypeElementDeclarationContext ctx) { }

    @Override public void enterAnnotationTypeElementModifier(JavaParser.AnnotationTypeElementModifierContext ctx) { }

    @Override public void exitAnnotationTypeElementModifier(JavaParser.AnnotationTypeElementModifierContext ctx) { }

    @Override public void enterDefaultValue(JavaParser.DefaultValueContext ctx) { }

    @Override public void exitDefaultValue(JavaParser.DefaultValueContext ctx) { }

    @Override public void enterAnnotation(JavaParser.AnnotationContext ctx) {
        tokens.add("ANNOTATION");
    }

    @Override public void exitAnnotation(JavaParser.AnnotationContext ctx) { }

    @Override public void enterNormalAnnotation(JavaParser.NormalAnnotationContext ctx) { }

    @Override public void exitNormalAnnotation(JavaParser.NormalAnnotationContext ctx) { }

    @Override public void enterElementValuePairList(JavaParser.ElementValuePairListContext ctx) { }

    @Override public void exitElementValuePairList(JavaParser.ElementValuePairListContext ctx) { }

    @Override public void enterElementValuePair(JavaParser.ElementValuePairContext ctx) { }

    @Override public void exitElementValuePair(JavaParser.ElementValuePairContext ctx) { }

    @Override public void enterElementValue(JavaParser.ElementValueContext ctx) { }

    @Override public void exitElementValue(JavaParser.ElementValueContext ctx) { }

    @Override public void enterElementValueArrayInitializer(JavaParser.ElementValueArrayInitializerContext ctx) { }

    @Override public void exitElementValueArrayInitializer(JavaParser.ElementValueArrayInitializerContext ctx) { }

    @Override public void enterElementValueList(JavaParser.ElementValueListContext ctx) { }

    @Override public void exitElementValueList(JavaParser.ElementValueListContext ctx) { }

    @Override public void enterMarkerAnnotation(JavaParser.MarkerAnnotationContext ctx) { }

    @Override public void exitMarkerAnnotation(JavaParser.MarkerAnnotationContext ctx) { }

    @Override public void enterSingleElementAnnotation(JavaParser.SingleElementAnnotationContext ctx) { }

    @Override public void exitSingleElementAnnotation(JavaParser.SingleElementAnnotationContext ctx) { }

    @Override public void enterArrayInitializer(JavaParser.ArrayInitializerContext ctx) {
        tokens.add("ARRAY_INIT {");
    }

    @Override public void exitArrayInitializer(JavaParser.ArrayInitializerContext ctx) {
        tokens.add("} ARRAY_INIT");
    }

    @Override public void enterVariableInitializerList(JavaParser.VariableInitializerListContext ctx) { }

    @Override public void exitVariableInitializerList(JavaParser.VariableInitializerListContext ctx) { }

    @Override public void enterBlock(JavaParser.BlockContext ctx) { }

    @Override public void exitBlock(JavaParser.BlockContext ctx) { }

    @Override public void enterBlockStatements(JavaParser.BlockStatementsContext ctx) { }

    @Override public void exitBlockStatements(JavaParser.BlockStatementsContext ctx) { }

    @Override public void enterBlockStatement(JavaParser.BlockStatementContext ctx) { }

    @Override public void exitBlockStatement(JavaParser.BlockStatementContext ctx) { }

    @Override public void enterLocalVariableDeclarationStatement(JavaParser.LocalVariableDeclarationStatementContext ctx) { }

    @Override public void exitLocalVariableDeclarationStatement(JavaParser.LocalVariableDeclarationStatementContext ctx) { }

    @Override public void enterLocalVariableDeclaration(JavaParser.LocalVariableDeclarationContext ctx) { }

    @Override public void exitLocalVariableDeclaration(JavaParser.LocalVariableDeclarationContext ctx) { }

    @Override public void enterStatement(JavaParser.StatementContext ctx) { }

    @Override public void exitStatement(JavaParser.StatementContext ctx) { }

    @Override public void enterStatementNoShortIf(JavaParser.StatementNoShortIfContext ctx) { }

    @Override public void exitStatementNoShortIf(JavaParser.StatementNoShortIfContext ctx) { }

    @Override public void enterStatementWithoutTrailingSubstatement(JavaParser.StatementWithoutTrailingSubstatementContext ctx) { }

    @Override public void exitStatementWithoutTrailingSubstatement(JavaParser.StatementWithoutTrailingSubstatementContext ctx) { }

    @Override public void enterEmptyStatement(JavaParser.EmptyStatementContext ctx) { }

    @Override public void exitEmptyStatement(JavaParser.EmptyStatementContext ctx) { }

    @Override public void enterLabeledStatement(JavaParser.LabeledStatementContext ctx) { }

    @Override public void exitLabeledStatement(JavaParser.LabeledStatementContext ctx) { }

    @Override public void enterLabeledStatementNoShortIf(JavaParser.LabeledStatementNoShortIfContext ctx) { }

    @Override public void exitLabeledStatementNoShortIf(JavaParser.LabeledStatementNoShortIfContext ctx) { }

    @Override public void enterExpressionStatement(JavaParser.ExpressionStatementContext ctx) { }

    @Override public void exitExpressionStatement(JavaParser.ExpressionStatementContext ctx) { }

    @Override public void enterStatementExpression(JavaParser.StatementExpressionContext ctx) { }

    @Override public void exitStatementExpression(JavaParser.StatementExpressionContext ctx) { }

    @Override public void enterIfThenStatement(JavaParser.IfThenStatementContext ctx) {
        tokens.add("IF {");
    }

    @Override public void exitIfThenStatement(JavaParser.IfThenStatementContext ctx) {
        tokens.add("} IF");
    }

    @Override public void enterIfThenElseStatement(JavaParser.IfThenElseStatementContext ctx) { }

    @Override public void exitIfThenElseStatement(JavaParser.IfThenElseStatementContext ctx) { }

    @Override public void enterIfThenElseStatementNoShortIf(JavaParser.IfThenElseStatementNoShortIfContext ctx) { }

    @Override public void exitIfThenElseStatementNoShortIf(JavaParser.IfThenElseStatementNoShortIfContext ctx) { }

    @Override public void enterAssertStatement(JavaParser.AssertStatementContext ctx) {
        tokens.add("ASSERT");
    }

    @Override public void exitAssertStatement(JavaParser.AssertStatementContext ctx) { }

    @Override public void enterSwitchStatement(JavaParser.SwitchStatementContext ctx) {
        tokens.add("SWITCH {");
    }

    @Override public void exitSwitchStatement(JavaParser.SwitchStatementContext ctx) {
        tokens.add("} SWITCH");
    }

    @Override public void enterSwitchBlock(JavaParser.SwitchBlockContext ctx) { }

    @Override public void exitSwitchBlock(JavaParser.SwitchBlockContext ctx) { }

    @Override public void enterSwitchBlockStatementGroup(JavaParser.SwitchBlockStatementGroupContext ctx) { }

    @Override public void exitSwitchBlockStatementGroup(JavaParser.SwitchBlockStatementGroupContext ctx) { }

    @Override public void enterSwitchLabels(JavaParser.SwitchLabelsContext ctx) { }

    @Override public void exitSwitchLabels(JavaParser.SwitchLabelsContext ctx) { }

    @Override public void enterSwitchLabel(JavaParser.SwitchLabelContext ctx) {
        tokens.add("CASE");
    }

    @Override public void exitSwitchLabel(JavaParser.SwitchLabelContext ctx) { }

    @Override public void enterEnumConstantName(JavaParser.EnumConstantNameContext ctx) { }

    @Override public void exitEnumConstantName(JavaParser.EnumConstantNameContext ctx) { }

    @Override public void enterWhileStatement(JavaParser.WhileStatementContext ctx) {
        tokens.add("WHILE {");
    }

    @Override public void exitWhileStatement(JavaParser.WhileStatementContext ctx) {
        tokens.add("} WHILE");
    }

    @Override public void enterWhileStatementNoShortIf(JavaParser.WhileStatementNoShortIfContext ctx) { }

    @Override public void exitWhileStatementNoShortIf(JavaParser.WhileStatementNoShortIfContext ctx) { }

    @Override public void enterDoStatement(JavaParser.DoStatementContext ctx) {
        tokens.add("DO {");
    }

    @Override public void exitDoStatement(JavaParser.DoStatementContext ctx) {
        tokens.add("} DO");
    }

    @Override public void enterForStatement(JavaParser.ForStatementContext ctx) {
        tokens.add("FOR {");
    }

    @Override public void exitForStatement(JavaParser.ForStatementContext ctx) {
        tokens.add("} FOR");
    }

    @Override public void enterForStatementNoShortIf(JavaParser.ForStatementNoShortIfContext ctx) { }

    @Override public void exitForStatementNoShortIf(JavaParser.ForStatementNoShortIfContext ctx) { }

    @Override public void enterBasicForStatement(JavaParser.BasicForStatementContext ctx) { }

    @Override public void exitBasicForStatement(JavaParser.BasicForStatementContext ctx) { }

    @Override public void enterBasicForStatementNoShortIf(JavaParser.BasicForStatementNoShortIfContext ctx) { }

    @Override public void exitBasicForStatementNoShortIf(JavaParser.BasicForStatementNoShortIfContext ctx) { }

    @Override public void enterForInit(JavaParser.ForInitContext ctx) { }

    @Override public void exitForInit(JavaParser.ForInitContext ctx) { }

    @Override public void enterForUpdate(JavaParser.ForUpdateContext ctx) { }

    @Override public void exitForUpdate(JavaParser.ForUpdateContext ctx) { }

    @Override public void enterStatementExpressionList(JavaParser.StatementExpressionListContext ctx) { }

    @Override public void exitStatementExpressionList(JavaParser.StatementExpressionListContext ctx) { }

    @Override public void enterEnhancedForStatement(JavaParser.EnhancedForStatementContext ctx) { }

    @Override public void exitEnhancedForStatement(JavaParser.EnhancedForStatementContext ctx) { }

    @Override public void enterEnhancedForStatementNoShortIf(JavaParser.EnhancedForStatementNoShortIfContext ctx) { }

    @Override public void exitEnhancedForStatementNoShortIf(JavaParser.EnhancedForStatementNoShortIfContext ctx) { }

    @Override public void enterBreakStatement(JavaParser.BreakStatementContext ctx) {
        tokens.add("BREAK");
    }

    @Override public void exitBreakStatement(JavaParser.BreakStatementContext ctx) { }

    @Override public void enterContinueStatement(JavaParser.ContinueStatementContext ctx) {
        tokens.add("CONTINUE");
    }

    @Override public void exitContinueStatement(JavaParser.ContinueStatementContext ctx) { }

    @Override public void enterReturnStatement(JavaParser.ReturnStatementContext ctx) {
        tokens.add("RETURN");
    }

    @Override public void exitReturnStatement(JavaParser.ReturnStatementContext ctx) { }

    @Override public void enterThrowStatement(JavaParser.ThrowStatementContext ctx) {
        tokens.add("THROW");
    }

    @Override public void exitThrowStatement(JavaParser.ThrowStatementContext ctx) { }

    @Override public void enterSynchronizedStatement(JavaParser.SynchronizedStatementContext ctx) {
        tokens.add("SYNC {");
    }

    @Override public void exitSynchronizedStatement(JavaParser.SynchronizedStatementContext ctx) {
        tokens.add("} SYNC");
    }

    @Override public void enterTryStatement(JavaParser.TryStatementContext ctx) {
        tokens.add("TRY {");
    }

    @Override public void exitTryStatement(JavaParser.TryStatementContext ctx) {
        if (hasFinally(ctx)) {
            tokens.add("FINALLY");
        }
    }

    private boolean hasFinally(JavaParser.TryStatementContext ctx) {
        for (ParseTree pt : ctx.children) {
            if (pt instanceof TerminalNode) {
                if (pt.getText().equals("finally")) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override public void enterCatches(JavaParser.CatchesContext ctx) { }

    @Override public void exitCatches(JavaParser.CatchesContext ctx) { }

    @Override public void enterCatchClause(JavaParser.CatchClauseContext ctx) {
        tokens.add("CATCH {");
    }

    @Override public void exitCatchClause(JavaParser.CatchClauseContext ctx) {
        tokens.add("} CATCH");
    }

    @Override public void enterCatchFormalParameter(JavaParser.CatchFormalParameterContext ctx) { }

    @Override public void exitCatchFormalParameter(JavaParser.CatchFormalParameterContext ctx) { }

    @Override public void enterCatchType(JavaParser.CatchTypeContext ctx) { }

    @Override public void exitCatchType(JavaParser.CatchTypeContext ctx) { }

    @Override public void enterFinally_(JavaParser.Finally_Context ctx) { }

    @Override public void exitFinally_(JavaParser.Finally_Context ctx) { }

    @Override public void enterTryWithResourcesStatement(JavaParser.TryWithResourcesStatementContext ctx) { }

    @Override public void exitTryWithResourcesStatement(JavaParser.TryWithResourcesStatementContext ctx) { }

    @Override public void enterResourceSpecification(JavaParser.ResourceSpecificationContext ctx) { }

    @Override public void exitResourceSpecification(JavaParser.ResourceSpecificationContext ctx) { }

    @Override public void enterResourceList(JavaParser.ResourceListContext ctx) { }

    @Override public void exitResourceList(JavaParser.ResourceListContext ctx) { }

    @Override public void enterResource(JavaParser.ResourceContext ctx) {
        tokens.add("TRY_WITH_RESOURCE");
    }

    @Override public void exitResource(JavaParser.ResourceContext ctx) { }

    @Override public void enterPrimary(JavaParser.PrimaryContext ctx) { }

    @Override public void exitPrimary(JavaParser.PrimaryContext ctx) { }

    @Override public void enterPrimaryNoNewArray(JavaParser.PrimaryNoNewArrayContext ctx) { }

    @Override public void exitPrimaryNoNewArray(JavaParser.PrimaryNoNewArrayContext ctx) { }

    @Override public void enterPrimaryNoNewArray_lf_arrayAccess(JavaParser.PrimaryNoNewArray_lf_arrayAccessContext ctx) { }

    @Override public void exitPrimaryNoNewArray_lf_arrayAccess(JavaParser.PrimaryNoNewArray_lf_arrayAccessContext ctx) { }

    @Override public void enterPrimaryNoNewArray_lfno_arrayAccess(JavaParser.PrimaryNoNewArray_lfno_arrayAccessContext ctx) { }

    @Override public void exitPrimaryNoNewArray_lfno_arrayAccess(JavaParser.PrimaryNoNewArray_lfno_arrayAccessContext ctx) { }

    @Override public void enterPrimaryNoNewArray_lf_primary(JavaParser.PrimaryNoNewArray_lf_primaryContext ctx) { }

    @Override public void exitPrimaryNoNewArray_lf_primary(JavaParser.PrimaryNoNewArray_lf_primaryContext ctx) { }

    @Override public void enterPrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primary(JavaParser.PrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primaryContext ctx) { }

    @Override public void exitPrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primary(JavaParser.PrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primaryContext ctx) { }

    @Override public void enterPrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primary(JavaParser.PrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primaryContext ctx) { }

    @Override public void exitPrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primary(JavaParser.PrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primaryContext ctx) { }

    @Override public void enterPrimaryNoNewArray_lfno_primary(JavaParser.PrimaryNoNewArray_lfno_primaryContext ctx) { }

    @Override public void exitPrimaryNoNewArray_lfno_primary(JavaParser.PrimaryNoNewArray_lfno_primaryContext ctx) { }

    @Override public void enterPrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primary(JavaParser.PrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primaryContext ctx) { }

    @Override public void exitPrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primary(JavaParser.PrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primaryContext ctx) { }

    @Override public void enterPrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primary(JavaParser.PrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primaryContext ctx) { }

    @Override public void exitPrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primary(JavaParser.PrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primaryContext ctx) { }

    @Override public void enterClassInstanceCreationExpression(JavaParser.ClassInstanceCreationExpressionContext ctx) { }

    @Override public void exitClassInstanceCreationExpression(JavaParser.ClassInstanceCreationExpressionContext ctx) { }

    @Override public void enterClassInstanceCreationExpression_lf_primary(JavaParser.ClassInstanceCreationExpression_lf_primaryContext ctx) { }

    @Override public void exitClassInstanceCreationExpression_lf_primary(JavaParser.ClassInstanceCreationExpression_lf_primaryContext ctx) { }

    @Override public void enterClassInstanceCreationExpression_lfno_primary(JavaParser.ClassInstanceCreationExpression_lfno_primaryContext ctx) { }

    @Override public void exitClassInstanceCreationExpression_lfno_primary(JavaParser.ClassInstanceCreationExpression_lfno_primaryContext ctx) { }

    @Override public void enterTypeArgumentsOrDiamond(JavaParser.TypeArgumentsOrDiamondContext ctx) { }

    @Override public void exitTypeArgumentsOrDiamond(JavaParser.TypeArgumentsOrDiamondContext ctx) { }

    @Override public void enterFieldAccess(JavaParser.FieldAccessContext ctx) { }

    @Override public void exitFieldAccess(JavaParser.FieldAccessContext ctx) { }

    @Override public void enterFieldAccess_lf_primary(JavaParser.FieldAccess_lf_primaryContext ctx) { }

    @Override public void exitFieldAccess_lf_primary(JavaParser.FieldAccess_lf_primaryContext ctx) { }

    @Override public void enterFieldAccess_lfno_primary(JavaParser.FieldAccess_lfno_primaryContext ctx) { }

    @Override public void exitFieldAccess_lfno_primary(JavaParser.FieldAccess_lfno_primaryContext ctx) { }

    @Override public void enterArrayAccess(JavaParser.ArrayAccessContext ctx) { }

    @Override public void exitArrayAccess(JavaParser.ArrayAccessContext ctx) { }

    @Override public void enterArrayAccess_lf_primary(JavaParser.ArrayAccess_lf_primaryContext ctx) { }

    @Override public void exitArrayAccess_lf_primary(JavaParser.ArrayAccess_lf_primaryContext ctx) { }

    @Override public void enterArrayAccess_lfno_primary(JavaParser.ArrayAccess_lfno_primaryContext ctx) { }

    @Override public void exitArrayAccess_lfno_primary(JavaParser.ArrayAccess_lfno_primaryContext ctx) { }

    @Override public void enterMethodInvocation(JavaParser.MethodInvocationContext ctx) { }

    @Override public void exitMethodInvocation(JavaParser.MethodInvocationContext ctx) { }

    @Override public void enterMethodInvocation_lf_primary(JavaParser.MethodInvocation_lf_primaryContext ctx) { }

    @Override public void exitMethodInvocation_lf_primary(JavaParser.MethodInvocation_lf_primaryContext ctx) { }

    @Override public void enterMethodInvocation_lfno_primary(JavaParser.MethodInvocation_lfno_primaryContext ctx) { }

    @Override public void exitMethodInvocation_lfno_primary(JavaParser.MethodInvocation_lfno_primaryContext ctx) { }

    @Override public void enterArgumentList(JavaParser.ArgumentListContext ctx) { }

    @Override public void exitArgumentList(JavaParser.ArgumentListContext ctx) { }

    @Override public void enterMethodReference(JavaParser.MethodReferenceContext ctx) { }

    @Override public void exitMethodReference(JavaParser.MethodReferenceContext ctx) { }

    @Override public void enterMethodReference_lf_primary(JavaParser.MethodReference_lf_primaryContext ctx) { }

    @Override public void exitMethodReference_lf_primary(JavaParser.MethodReference_lf_primaryContext ctx) { }

    @Override public void enterMethodReference_lfno_primary(JavaParser.MethodReference_lfno_primaryContext ctx) { }

    @Override public void exitMethodReference_lfno_primary(JavaParser.MethodReference_lfno_primaryContext ctx) { }

    @Override public void enterArrayCreationExpression(JavaParser.ArrayCreationExpressionContext ctx) { }

    @Override public void exitArrayCreationExpression(JavaParser.ArrayCreationExpressionContext ctx) { }

    @Override public void enterDimExprs(JavaParser.DimExprsContext ctx) { }

    @Override public void exitDimExprs(JavaParser.DimExprsContext ctx) { }

    @Override public void enterDimExpr(JavaParser.DimExprContext ctx) { }

    @Override public void exitDimExpr(JavaParser.DimExprContext ctx) { }

    @Override public void enterConstantExpression(JavaParser.ConstantExpressionContext ctx) { }

    @Override public void exitConstantExpression(JavaParser.ConstantExpressionContext ctx) { }

    @Override public void enterExpression(JavaParser.ExpressionContext ctx) { }

    @Override public void exitExpression(JavaParser.ExpressionContext ctx) { }

    @Override public void enterLambdaExpression(JavaParser.LambdaExpressionContext ctx) { }

    @Override public void exitLambdaExpression(JavaParser.LambdaExpressionContext ctx) { }

    @Override public void enterLambdaParameters(JavaParser.LambdaParametersContext ctx) { }

    @Override public void exitLambdaParameters(JavaParser.LambdaParametersContext ctx) { }

    @Override public void enterInferredFormalParameterList(JavaParser.InferredFormalParameterListContext ctx) { }

    @Override public void exitInferredFormalParameterList(JavaParser.InferredFormalParameterListContext ctx) { }

    @Override public void enterLambdaBody(JavaParser.LambdaBodyContext ctx) { }

    @Override public void exitLambdaBody(JavaParser.LambdaBodyContext ctx) { }

    @Override public void enterAssignmentExpression(JavaParser.AssignmentExpressionContext ctx) {
        tokens.add("APPLY");
    }

    @Override public void exitAssignmentExpression(JavaParser.AssignmentExpressionContext ctx) { }

    @Override public void enterAssignment(JavaParser.AssignmentContext ctx) { }

    @Override public void exitAssignment(JavaParser.AssignmentContext ctx) { }

    @Override public void enterLeftHandSide(JavaParser.LeftHandSideContext ctx) { }

    @Override public void exitLeftHandSide(JavaParser.LeftHandSideContext ctx) { }

    @Override public void enterAssignmentOperator(JavaParser.AssignmentOperatorContext ctx) { }

    @Override public void exitAssignmentOperator(JavaParser.AssignmentOperatorContext ctx) { }

    @Override public void enterConditionalExpression(JavaParser.ConditionalExpressionContext ctx) { }

    @Override public void exitConditionalExpression(JavaParser.ConditionalExpressionContext ctx) { }

    @Override public void enterConditionalOrExpression(JavaParser.ConditionalOrExpressionContext ctx) { }

    @Override public void exitConditionalOrExpression(JavaParser.ConditionalOrExpressionContext ctx) { }

    @Override public void enterConditionalAndExpression(JavaParser.ConditionalAndExpressionContext ctx) { }

    @Override public void exitConditionalAndExpression(JavaParser.ConditionalAndExpressionContext ctx) { }

    @Override public void enterInclusiveOrExpression(JavaParser.InclusiveOrExpressionContext ctx) { }

    @Override public void exitInclusiveOrExpression(JavaParser.InclusiveOrExpressionContext ctx) { }

    @Override public void enterExclusiveOrExpression(JavaParser.ExclusiveOrExpressionContext ctx) { }

    @Override public void exitExclusiveOrExpression(JavaParser.ExclusiveOrExpressionContext ctx) { }

    @Override public void enterAndExpression(JavaParser.AndExpressionContext ctx) { }

    @Override public void exitAndExpression(JavaParser.AndExpressionContext ctx) { }

    @Override public void enterEqualityExpression(JavaParser.EqualityExpressionContext ctx) { }

    @Override public void exitEqualityExpression(JavaParser.EqualityExpressionContext ctx) { }

    @Override public void enterRelationalExpression(JavaParser.RelationalExpressionContext ctx) { }

    @Override public void exitRelationalExpression(JavaParser.RelationalExpressionContext ctx) { }

    @Override public void enterShiftExpression(JavaParser.ShiftExpressionContext ctx) { }

    @Override public void exitShiftExpression(JavaParser.ShiftExpressionContext ctx) { }

    @Override public void enterAdditiveExpression(JavaParser.AdditiveExpressionContext ctx) { }

    @Override public void exitAdditiveExpression(JavaParser.AdditiveExpressionContext ctx) { }

    @Override public void enterMultiplicativeExpression(JavaParser.MultiplicativeExpressionContext ctx) { }

    @Override public void exitMultiplicativeExpression(JavaParser.MultiplicativeExpressionContext ctx) { }

    @Override public void enterUnaryExpression(JavaParser.UnaryExpressionContext ctx) { }

    @Override public void exitUnaryExpression(JavaParser.UnaryExpressionContext ctx) { }

    @Override public void enterPreIncrementExpression(JavaParser.PreIncrementExpressionContext ctx) { }

    @Override public void exitPreIncrementExpression(JavaParser.PreIncrementExpressionContext ctx) { }

    @Override public void enterPreDecrementExpression(JavaParser.PreDecrementExpressionContext ctx) { }

    @Override public void exitPreDecrementExpression(JavaParser.PreDecrementExpressionContext ctx) { }

    @Override public void enterUnaryExpressionNotPlusMinus(JavaParser.UnaryExpressionNotPlusMinusContext ctx) { }

    @Override public void exitUnaryExpressionNotPlusMinus(JavaParser.UnaryExpressionNotPlusMinusContext ctx) { }

    @Override public void enterPostfixExpression(JavaParser.PostfixExpressionContext ctx) { }

    @Override public void exitPostfixExpression(JavaParser.PostfixExpressionContext ctx) { }

    @Override public void enterPostIncrementExpression(JavaParser.PostIncrementExpressionContext ctx) { }

    @Override public void exitPostIncrementExpression(JavaParser.PostIncrementExpressionContext ctx) { }

    @Override public void enterPostIncrementExpression_lf_postfixExpression(JavaParser.PostIncrementExpression_lf_postfixExpressionContext ctx) { }

    @Override public void exitPostIncrementExpression_lf_postfixExpression(JavaParser.PostIncrementExpression_lf_postfixExpressionContext ctx) { }

    @Override public void enterPostDecrementExpression(JavaParser.PostDecrementExpressionContext ctx) { }

    @Override public void exitPostDecrementExpression(JavaParser.PostDecrementExpressionContext ctx) { }

    @Override public void enterPostDecrementExpression_lf_postfixExpression(JavaParser.PostDecrementExpression_lf_postfixExpressionContext ctx) { }

    @Override public void exitPostDecrementExpression_lf_postfixExpression(JavaParser.PostDecrementExpression_lf_postfixExpressionContext ctx) { }

    @Override public void enterCastExpression(JavaParser.CastExpressionContext ctx) { }

    @Override public void exitCastExpression(JavaParser.CastExpressionContext ctx) { }


    @Override public void enterEveryRule(ParserRuleContext ctx) { }

    @Override public void exitEveryRule(ParserRuleContext ctx) { }

    @Override public void visitTerminal(TerminalNode node) {
        if (node.getText().equals("else")) {
            tokens.add("ELSE ");
        }
    }

    @Override public void visitErrorNode(ErrorNode node) { }

}

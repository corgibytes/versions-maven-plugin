package org.codehaus.mojo.versions.filtering;

import org.apache.maven.model.Dependency;
import org.codehaus.mojo.versions.DependencyBuilder;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayNameGeneration( DisplayNameGenerator.ReplaceUnderscores.class )
class TokenizedMatcherTest
{

    @Nested
    class ExactMatchPattern
    {

        private final TokenizedMatcher matcher = TokenizedMatcher
                .parse( "group:artifact:1.0:jar:tests:compile" );


        @Test
        void accepts_exact_match()
        {
            Dependency input = DependencyBuilder.dependencyWith(
                    "group",
                    "artifact",
                    "1.0",
                    "jar",
                    "tests",
                    "compile" );

            boolean actual = matcher.test( input );

            assertTrue( actual );
        }


        @ParameterizedTest
        @CsvSource( {
                "xxxxx, artifact, 1.0, jar, tests, compile",
                "group, xxxxxxxx, 1.0, jar, tests, compile",
                "group, artifact, xxx, jar, tests, compile",
                "group, artifact, 1.0, xxx, tests, compile",
                "group, artifact, 1.0, jar, xxxxx, compile",
                "group, artifact, 1.0, jar, tests, xxxxxxx",
        } )
        void rejects_differing_fields(
                String group, String artifact, String version, String type, String classifier, String scope
        )
        {

            Dependency input = DependencyBuilder.dependencyWith(
                    group,
                    artifact,
                    version,
                    type,
                    classifier,
                    scope );

            boolean actual = matcher.test( input );

            assertFalse( actual );
        }
    }

    @Nested
    class WildcardPattern
    {

        @Test
        void accepts_wildcards()
        {
            Dependency input = DependencyBuilder.dependencyWith(
                    "foo",
                    "my-api",
                    "foo",
                    "foo",
                    "foo",
                    "foo" );

            TokenizedMatcher matcher = TokenizedMatcher
                    .parse( "*:my-api" );

            boolean actual = matcher.test( input );

            assertTrue( actual );
        }

        @Nested
        class NullClassifier
        {
            private final DependencyBuilder depBuilder = DependencyBuilder.newBuilder(
                    "foo",
                    "foo",
                    "foo",
                    "foo",
                    "foo",
                    "foo" );

            private final TokenizedMatcher matcher = TokenizedMatcher
                    .parse( "*:*:*:*:null:*" );

            @Test
            void accepts_null_scope()
            {
                Dependency input = depBuilder.withClassifier( null ).build();

                boolean actual = matcher.test( input );

                assertTrue( actual );
            }

            @Test
            void rejects_nonnull_scope()
            {
                Dependency input = depBuilder.withClassifier( "tests" ).build();

                boolean actual = matcher.test( input );

                assertFalse( actual );
            }
        }

        @Nested
        class NullScope
        {
            private final DependencyBuilder depBuilder = DependencyBuilder.newBuilder(
                    "foo",
                    "foo",
                    "foo",
                    "foo",
                    "foo",
                    "foo" );

            private final TokenizedMatcher matcher = TokenizedMatcher
                    .parse( "*:*:*:*:*:null" );

            @Test
            void accepts_null_scope()
            {
                Dependency input = depBuilder.withScope( null ).build();

                boolean actual = matcher.test( input );

                assertTrue( actual );
            }

            @Test
            void rejects_nonnull_scope()
            {
                Dependency input = depBuilder.withScope( "compile" ).build();

                boolean actual = matcher.test( input );

                assertFalse( actual );
            }
        }

    }

}

/*
 * SonarQube PHP Plugin
 * Copyright (C) 2010-2017 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.php.tree.impl.expression;

import com.sonar.sslr.api.typed.ActionParser;
import java.util.Collections;
import org.junit.Test;
import org.sonar.php.parser.PHPLexicalGrammar;
import org.sonar.php.parser.PHPParserBuilder;
import org.sonar.plugins.php.api.tree.Tree;
import org.sonar.plugins.php.api.tree.expression.HeredocBodyTree;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;

import static org.assertj.core.api.Assertions.assertThat;

public class HeredocBodyTreeTest {

  @Test
  public void heredoc_body() throws Exception {
    ActionParser<Tree> parser = PHPParserBuilder.createParser(PHPLexicalGrammar.HEREDOC_BODY, 0);
    HeredocBodyTree heredoc = (HeredocBodyTree) parser.parse("Start $name End");
    assertThat(heredoc.is(Tree.Kind.HEREDOC_BODY)).isTrue();
    assertThat(heredoc.expressions()).hasSize(3);
    assertThat(heredoc.expressions().get(0).is(Tree.Kind.HEREDOC_STRING_CHARACTERS)).isTrue();
    assertThat(heredoc.expressions().get(1).is(Tree.Kind.VARIABLE_IDENTIFIER)).isTrue();
    assertThat(heredoc.expressions().get(2).is(Tree.Kind.HEREDOC_STRING_CHARACTERS)).isTrue();
  }

  @Test(expected = IllegalStateException.class)
  public void invalid_accept() throws Exception {
    HeredocBodyTree tree = new HeredocBodyTreeImpl(Collections.emptyList());
    tree.accept(new PHPVisitorCheck() {
    });
  }
}

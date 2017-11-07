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

import java.util.Iterator;
import java.util.List;
import org.sonar.php.tree.impl.PHPTree;
import org.sonar.plugins.php.api.tree.Tree;
import org.sonar.plugins.php.api.tree.expression.ExpressionTree;
import org.sonar.plugins.php.api.tree.expression.HeredocBodyTree;
import org.sonar.plugins.php.api.visitors.VisitorCheck;

public class HeredocBodyTreeImpl extends PHPTree implements HeredocBodyTree {

  private final List<ExpressionTree> expressions;

  public HeredocBodyTreeImpl(List<ExpressionTree> expressions) {
    this.expressions = expressions;
  }

  @Override
  public Kind getKind() {
    return Kind.HEREDOC_BODY;
  }

  @Override
  public List<ExpressionTree> expressions() {
    return expressions;
  }

  @Override
  public Iterator<Tree> childrenIterator() {
    return (Iterator<Tree>)(Iterator<? extends Tree>)expressions.iterator();
  }

  @Override
  public void accept(VisitorCheck visitor) {
    throw new IllegalStateException("class HeredocBodyTreeImpl is used only internally for building the tree and should not be used to tree visiting.");
  }

}
